/**
 * 
 */
package eu.vargasoft.tools.dvdarchive;

import java.io.IOException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.vargasoft.tools.dvdarchive.model.Disc;
import eu.vargasoft.tools.dvdarchive.model.DiscType;
import eu.vargasoft.tools.dvdarchive.utils.ExecResult;
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
	 */
	public Set<String> getMountPoints() throws IOException {
		// String cmd = "lshw -C disk | grep sr | cut -d \":\" -f 2 | sort -u";

		ExecResult commandResult = commandExecutor.execute(UnixCommands.LSHW, "/dev/sr*");
		return UnixCommandExecutorHelper.getExactMountPoints(commandResult);
	}

	/**
	 * blkid /dev/sr1 /dev/sr1: UUID="2007-08-17-20-51-00-00" LABEL="FilmekX"
	 * TYPE="iso9660"
	 * 
	 * @param mountPoint
	 * @return disc information
	 * @throws IOException
	 */
	public Disc getDisc(String mountPoint) throws IOException {
		String cmd = UnixCommands.BLKID + mountPoint;
		ExecResult commandResult = commandExecutor.execute(cmd, null);
		// extract disc info
		if (commandResult.getStdOut().size() > 0) {
			String[] splits = commandResult.getStdOut().get(0).split("\"");
			return Disc.builder().label(splits[3]).mountPoint(mountPoint)
					.type(DiscType.valueOf(splits[5].toUpperCase())).build();
		}
		return null;
	}

	/**
	 * eject /dev/sr0
	 * 
	 * @param mountPoint
	 * @throws IOException
	 */
	public void openDisc(String mountPoint) throws IOException {
		String cmd = UnixCommands.EJECT + mountPoint;
		commandExecutor.execute(cmd, null);
	}

	/**
	 * eject -t /dev/sr0
	 * 
	 * @param mountPoint
	 * @throws IOException
	 */
	public void closeDisc(String mountPoint) throws IOException {
		String cmd = UnixCommands.EJECT_CLOSE + mountPoint;
		commandExecutor.execute(cmd, null);
	}

}
