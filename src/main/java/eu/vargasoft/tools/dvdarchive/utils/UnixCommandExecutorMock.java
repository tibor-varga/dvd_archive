/**
 * 
 */
package eu.vargasoft.tools.dvdarchive.utils;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.stereotype.Component;

import eu.vargasoft.tools.dvdarchive.model.ExecResult;
import lombok.extern.slf4j.Slf4j;

/**
 * @author buxi
 *
 */
@Slf4j
@Component
public class UnixCommandExecutorMock implements UnixCommandExecutorInterface {
	public static final String DEVSR0 = "/dev/sr0";
	public static final String DEVSR1 = "/dev/sr1";
	public static final String DEVSR2 = "/dev/sr2";
	public static final String DEVSR3 = "/dev/sr3";

	public static final String MEDIA_DVD0 = "/media/dvd0";
	public static final String MEDIA_DVD1 = "/media/dvd1";
	public static final String MEDIA_DVD2 = "/media/dvd2";
	public static final String MEDIA_DVD3 = "/media/dvd3";

	public static final String GETDISC_SR0 = String.format(UnixCommands.BLKID, DEVSR0);
	public static final String GETDISC_SR1 = String.format(UnixCommands.BLKID, DEVSR1);
	public static final String GETDISC_SR2 = String.format(UnixCommands.BLKID, DEVSR2);
	public static final String GETDISC_SR3 = String.format(UnixCommands.BLKID, DEVSR3);

	public static final String GET_EJECT_STATUS_SR0_NOT_MOUNTED = String.format(UnixCommands.EJECT_STATUS, DEVSR0);
	public static final String GET_EJECT_STATUS_SR1_MOUNTED = String.format(UnixCommands.EJECT_STATUS, DEVSR1);
	public static final String GET_EJECT_STATUS_SR2_MOUNTED = String.format(UnixCommands.EJECT_STATUS, DEVSR2);
	public static final String GET_EJECT_STATUS_SR3_MOUNTED = String.format(UnixCommands.EJECT_STATUS, DEVSR3);

	@Override
	public ExecResult execute(String command, String stdoutFilter) throws IOException, InterruptedException {
		// TODO refactor to Command or Visitor pattern
		log.info("executing command: {} with stdout filter: {}", command, stdoutFilter);
		if (command.equals(GETDISC_SR0)) {
			return ExecResult.builder().exitValue(0)
					.stdOut(Arrays
							.asList("/dev/sr0: UUID=\"2007-08-17-20-51-00-00\" LABEL=\"label0\" TYPE=\"iso9660\""))
					.build();
		} else if (command.equals(GETDISC_SR1)) {
			return ExecResult.builder().exitValue(0)
					.stdOut(Arrays.asList("/dev/sr1: UUID=\"2007-08-17-20-51-00-00\" LABEL=\"label1\" TYPE=\"udf\""))
					.build();

		} else if (command.equals(GETDISC_SR2)) {
			return ExecResult.builder().exitValue(0)
					.stdOut(Arrays.asList("/dev/sr2: UUID=\"2007-08-17-20-51-00-00\" LABEL=\"label2\" TYPE=\"udf\""))
					.build();

		} else if (command.equals(GETDISC_SR3)) {
			return ExecResult.builder().exitValue(0)
					.stdOut(Arrays
							.asList("/dev/sr3: UUID=\"2007-08-17-20-51-00-00\" LABEL=\"label3\" TYPE=\"iso9660\""))
					.build();

		} else if (command.equals(UnixCommands.LSHW)) {
			return ExecResult.builder().exitValue(0)
					.stdOut(Arrays.asList("logical name: /dev/sr0", " 	  logical name: /dev/sr1",
							"     logical name: /dev/sr2", "logical name: /dev/sr3", "logical name: /dev/sr3"))
					.build();

		} else if (command.equals(GET_EJECT_STATUS_SR0_NOT_MOUNTED)) {
			return ExecResult.builder().exitValue(0).stdOut(Arrays.asList("eject: `/dev/sr0' is not mounted")).build();

		} else if (command.equals(GET_EJECT_STATUS_SR1_MOUNTED)) {
			return ExecResult.builder().exitValue(0)
					.stdOut(Arrays.asList("eject: `/dev/sr1' is mounted at `/media/dvd1'")).build();

		} else if (command.equals(GET_EJECT_STATUS_SR2_MOUNTED)) {
			return ExecResult.builder().exitValue(0)
					.stdOut(Arrays.asList("eject: `/dev/sr2' is mounted at `/media/dvd2'")).build();

		} else if (command.equals(GET_EJECT_STATUS_SR3_MOUNTED)) {
			return ExecResult.builder().exitValue(0)
					.stdOut(Arrays.asList("eject: `/dev/sr3' is mounted at `/media/dvd3'")).build();

		} else {
			log.error("unknown command: " + command);
			return ExecResult.builder().exitValue(1).stdOut(Arrays.asList("unknown command: " + command)).build();
		}
	}
}
