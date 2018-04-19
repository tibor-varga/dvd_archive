/**
 * 
 */
package eu.vargasoft.tools.dvdarchive;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.vargasoft.tools.dvdarchive.model.CopyResult;
import eu.vargasoft.tools.dvdarchive.model.Disc;
import eu.vargasoft.tools.dvdarchive.model.DiscCopyUnixCommand;
import eu.vargasoft.tools.dvdarchive.model.ExecResult;
import eu.vargasoft.tools.dvdarchive.model.TrayInfo;
import eu.vargasoft.tools.dvdarchive.model.TrayStatus;
import eu.vargasoft.tools.dvdarchive.utils.UnixCommandExecutorHelper;
import eu.vargasoft.tools.dvdarchive.utils.UnixCommandExecutorInterface;
import eu.vargasoft.tools.dvdarchive.utils.UnixCommands;
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

	@Autowired
	ConfigurationProperties configProperties;

	public Map<String, CopyResult> copyAllDiscs() throws IOException, InterruptedException, ExecutionException {
		Set<String> mountPoints = discController.getMountPoints();

		Map<String, Future<CopyResult>> copyTasks = new HashMap<>();
		// initializing parallel execution
		// TODO refactor parameter should come from properties
		ExecutorService executor = Executors.newFixedThreadPool(5);

		for (String mountPoint : mountPoints) {
			Callable<CopyResult> task = () -> {
				try {
					return copyDisk(mountPoint);
				} catch (InterruptedException e) {
					log.warn("Interrupted!", e);
					Thread.currentThread().interrupt();
				}
				return null;
			};

			Future<CopyResult> future = executor.submit(task);
			copyTasks.put(mountPoint, future);

		}
		// waiting for all threads to finish
		checkFuturesAllDone(copyTasks.values());

		// generating copyResults and ejecting cd
		Map<String, CopyResult> copyStatus = new HashMap<>();
		for (Entry<String, Future<CopyResult>> copyTaskEntry : copyTasks.entrySet()) {
			String mountPoint = copyTaskEntry.getKey();
			copyStatus.put(mountPoint, copyTaskEntry.getValue().get());
			eject(mountPoint);
		}

		// shutting down the executor, to let the app to exit
		executor.shutdown();
		return copyStatus;
	}

	private void checkFuturesAllDone(Collection<Future<CopyResult>> collection) {
		boolean allDone;
		do {
			allDone = true;
			// TODO refactor: optimize exit condition
			for (Future<CopyResult> future : collection) {
				allDone = allDone && future.isDone();
			}
		} while (allDone);
	}

	private void eject(String mountPoint) throws IOException, InterruptedException {
		commandExecutor.execute(String.format(UnixCommands.EJECT, mountPoint), null);
	}

	public CopyResult copyDisk(String mountPoint) throws IOException, InterruptedException {
		log.info("processing mount point: {}", mountPoint);
		TrayInfo trayInfo = discController.getTrayInfo(mountPoint);
		log.debug("trayStatus: {}", trayInfo);
		if (trayInfo.getStatus().equals(TrayStatus.MOUNTED)) {
			// get disc info
			Disc disc = discController.getDisc(mountPoint, trayInfo);

			log.info("disc info: {}", disc);
			DiscCopyUnixCommand copyCommand = DiscCopyUnixCommandFactory.createUnixCommand(disc.getType());
			log.debug("copy command: {}", copyCommand);

			// create target directory
			String targetDir = configProperties.getMainTargetDir()
					+ UnixCommandExecutorHelper.generateDirectoryNameFromDiscLabel(disc.getLabel());
			commandExecutor.execute(String.format(UnixCommands.MKDIR, targetDir), null);

			// execute copy
			ExecResult copyResult = commandExecutor.execute(copyCommand.getUnixCommand(disc, targetDir), null);
			log.info("Copy finished: {}", copyResult);
			return CopyResult.builder().disc(disc).execResult(copyResult).build();
		} else {
			log.error("disc not mounted: {}", mountPoint);
			return null;
		}
	}
}
