/**
 * 
 */
package eu.vargasoft.tools.dvdarchive;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.vargasoft.tools.dvdarchive.model.CopyResult;
import eu.vargasoft.tools.dvdarchive.model.Disc;
import eu.vargasoft.tools.dvdarchive.model.DiscCopyUnixCommand;
import eu.vargasoft.tools.dvdarchive.model.ExecResult;
import eu.vargasoft.tools.dvdarchive.model.TrayInfo;
import eu.vargasoft.tools.dvdarchive.model.TrayStatus;
import eu.vargasoft.tools.dvdarchive.utils.UnixCommandExecutorInterface;
import lombok.extern.slf4j.Slf4j;

/**
 * @author buxi
 *
 */

@Slf4j
@Component
public class CopyManager {
	@Autowired
	DiscController discController;

	@Autowired
	UnixCommandExecutorInterface commandExecutor;

	private String destination;

	public HashMap<String, CopyResult> copyAllDiscs() throws IOException {
		HashMap<String, CopyResult> copyStatus = new HashMap<String, CopyResult>();
		Set<String> mountPoints = discController.getMountPoints();
		for (String mountPoint : mountPoints) {
			CopyResult copyResult = copyDisk(mountPoint);
			copyStatus.put(mountPoint, copyResult);
		}
		return copyStatus;
	}

	public CopyResult copyDisk(String mountPoint) throws IOException {
		log.info("processing mount point: {}", mountPoint);
		TrayInfo trayInfo = discController.getTrayInfo(mountPoint);
		log.debug("trayStatus: {}", trayInfo);
		if (trayInfo.getStatus().equals(TrayStatus.MOUNTED)) {
			// get disc info
			Disc disc = discController.getDisc(mountPoint, trayInfo);

			log.debug("disc info: {}", disc);
			DiscCopyUnixCommand copyCommand = DiscCopyUnixCommandFactory.createUnixCommand(disc.getType());
			log.debug("copy command: {}", copyCommand);

			// execute copy
			ExecResult copyResult = commandExecutor.execute(copyCommand.getUnixCommand(mountPoint, destination), null);
			log.info("Copy finished: {}", copyResult);
			return CopyResult.builder().disc(disc).execResult(copyResult).build();
		} else {
			log.error("disc not mounted: {}", mountPoint);
			return null;
		}
	}
}
