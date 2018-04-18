/**
 * 
 */
package eu.vargasoft.tools.dvdarchive;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.vargasoft.tools.dvdarchive.model.Disc;
import eu.vargasoft.tools.dvdarchive.model.DiscType;
import eu.vargasoft.tools.dvdarchive.model.ExecResult;
import eu.vargasoft.tools.dvdarchive.model.TrayInfo;
import eu.vargasoft.tools.dvdarchive.model.TrayStatus;
import eu.vargasoft.tools.dvdarchive.utils.UnixCommandExecutorHelper;
import eu.vargasoft.tools.dvdarchive.utils.UnixCommandExecutorInterface;
import eu.vargasoft.tools.dvdarchive.utils.UnixCommands;

/**
 * @author buxi
 *
 */
@Component
public class DiscController {
	@Autowired
	UnixCommandExecutorInterface commandExecutor;

	/**
	 * lshw -C disk | grep sr | cut -d \":\" -f 2 | sort -u
	 * 
	 * @return list of /dev/sr0, /dev/sr1
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public Set<String> getMountPoints() throws IOException, InterruptedException {
		// String cmd = "lshw -C disk | grep sr | cut -d \":\" -f 2 | sort -u";

		ExecResult commandResult = commandExecutor.execute(UnixCommands.LSHW, "/dev/sr*");
		return UnixCommandExecutorHelper.getExactMountPoints(commandResult);
	}

	/**
	 * blkid /dev/sr1 /dev/sr1: UUID="2007-08-17-20-51-00-00" LABEL="FilmekX"
	 * TYPE="iso9660" + tray status and directory info
	 * 
	 * @param mountPoint
	 * @param trayInfo
	 * @return disc information
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public Disc getDisc(String mountPoint, TrayInfo trayInfo) throws IOException, InterruptedException {
		String cmd = String.format(UnixCommands.BLKID, mountPoint);
		ExecResult commandResult = commandExecutor.execute(cmd, null);
		// extract disc info
		if (commandResult.getStdOut().size() > 0) {
			String[] splits = commandResult.getStdOut().get(0).split("\"");
			return Disc.builder().label(splits[3]).mountPoint(mountPoint)
					.type(DiscType.valueOf(splits[5].toUpperCase())).trayInfo(trayInfo).build();
		}
		return null;
	}

	/**
	 * eject /dev/sr0
	 * 
	 * @param mountPoint
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void openDisc(String mountPoint) throws IOException, InterruptedException {
		String cmd = String.format(UnixCommands.EJECT, mountPoint);
		commandExecutor.execute(cmd, null);
	}

	/**
	 * eject -t /dev/sr0
	 * 
	 * @param mountPoint
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void closeDisc(String mountPoint) throws IOException, InterruptedException {
		String cmd = String.format(UnixCommands.EJECT_CLOSE, mountPoint);
		commandExecutor.execute(cmd, null);
	}

	/**
	 * eject -n -v /dev/sr0
	 * 
	 * eject: `/dev/sr0' is mounted at `/media/dvd0'
	 * 
	 * @param mountPoint
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public TrayInfo getTrayInfo(String mountPoint) throws IOException, InterruptedException {
		String cmd = String.format(UnixCommands.EJECT_STATUS, mountPoint);
		ExecResult commandResult = commandExecutor.execute(cmd, "mounted");
		// extract disc info
		if (commandResult.getStdOut().size() > 0) {
			String commandResultFirstLine = commandResult.getStdOut().get(0);
			if (commandResultFirstLine.contains("not")) {
				return TrayInfo.builder().status(TrayStatus.NOT_MOUNTED).build();
			} else {
				String directory = commandResultFirstLine.split("`")[2].replaceAll("'", "");
				return TrayInfo.builder().status(TrayStatus.MOUNTED).directory(directory).build();
			}
		} else {
			throw new InvalidParameterException("Error by getting status for: " + mountPoint);
		}
	}
}
