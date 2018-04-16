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
import eu.vargasoft.tools.dvdarchive.utils.UnixCommandExecutor;

/**
 * @author buxi
 *
 */
@Component
public class DiscController {
	@Autowired
	UnixCommandExecutor commandExecutor;

	public Set<String> getMountPoints() throws IOException {
		// String cmd = "lshw -C disk | grep sr | cut -d \":\" -f 2 | sort -u";
		String cmd = "lshw -C disk";
		ExecResult commandResult = commandExecutor.execute(cmd, "/dev/sr*");
		return UnixCommandExecutor.getExactMountPoints(commandResult);
	}

	/**
	 * blkid /dev/sr1 /dev/sr1: UUID="2007-08-17-20-51-00-00" LABEL="Filmek 31.
	 * mese" TYPE="iso9660"
	 * 
	 * @param mountPoint
	 * @throws IOException
	 */
	public Disc getDisc(String mountPoint) throws IOException {
		String cmd = "blkid " + mountPoint;
		ExecResult commandResult = commandExecutor.execute(cmd, "*");
		// extract disc info
		if (commandResult.getStdOut().size() > 0) {
			String[] splits = commandResult.getStdOut().get(0).split("\"");
			return Disc.builder().label(splits[3]).mountPoint(mountPoint).type(DiscType.valueOf(splits[5])).build();
		}
		return null;
	}
}
