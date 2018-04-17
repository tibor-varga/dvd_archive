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
	public final static String DEVSR0 = "/dev/sr0";
	public final static String DEVSR1 = "/dev/sr1";
	public final static String DEVSR2 = "/dev/sr2";
	public final static String DEVSR3 = "/dev/sr3";

	public final static String MEDIA_DVD0 = "/media/dvd0";
	public final static String MEDIA_DVD1 = "/media/dvd1";
	public final static String MEDIA_DVD2 = "/media/dvd2";
	public final static String MEDIA_DVD3 = "/media/dvd3";

	public final static String GETDISC_SR0 = UnixCommands.BLKID + DEVSR0;
	public final static String GETDISC_SR1 = UnixCommands.BLKID + DEVSR1;
	public final static String GETDISC_SR2 = UnixCommands.BLKID + DEVSR2;
	public final static String GETDISC_SR3 = UnixCommands.BLKID + DEVSR3;

	public final static String GET_EJECT_STATUS_SR0_NOT_MOUNTED = UnixCommands.EJECT_STATUS + DEVSR0;
	public final static String GET_EJECT_STATUS_SR1_MOUNTED = UnixCommands.EJECT_STATUS + DEVSR1;
	public final static String GET_EJECT_STATUS_SR2_MOUNTED = UnixCommands.EJECT_STATUS + DEVSR2;
	public final static String GET_EJECT_STATUS_SR3_MOUNTED = UnixCommands.EJECT_STATUS + DEVSR3;

	@Override
	public ExecResult execute(String command, String stdoutFilter) throws IOException {
		switch (command) {
		case GETDISC_SR0:
			return ExecResult.builder().exitValue(0)
					.stdOut(Arrays
							.asList("/dev/sr0: UUID=\"2007-08-17-20-51-00-00\" LABEL=\"label0\" TYPE=\"iso9660\""))
					.build();

		case GETDISC_SR1:
			return ExecResult.builder().exitValue(0)
					.stdOut(Arrays.asList("/dev/sr1: UUID=\"2007-08-17-20-51-00-00\" LABEL=\"label1\" TYPE=\"udf\""))
					.build();

		case GETDISC_SR2:
			return ExecResult.builder().exitValue(0)
					.stdOut(Arrays.asList("/dev/sr2: UUID=\"2007-08-17-20-51-00-00\" LABEL=\"label2\" TYPE=\"udf\""))
					.build();

		case GETDISC_SR3:
			return ExecResult.builder().exitValue(0)
					.stdOut(Arrays
							.asList("/dev/sr3: UUID=\"2007-08-17-20-51-00-00\" LABEL=\"label3\" TYPE=\"iso9660\""))
					.build();

		case UnixCommands.LSHW:
			return ExecResult.builder().exitValue(0)
					.stdOut(Arrays.asList("logical name: /dev/sr0", " 	  logical name: /dev/sr1",
							"     logical name: /dev/sr2", "logical name: /dev/sr3", "logical name: /dev/sr3"))
					.build();

		case GET_EJECT_STATUS_SR0_NOT_MOUNTED:
			return ExecResult.builder().exitValue(0).stdOut(Arrays.asList("eject: `/dev/sr0' is not mounted")).build();

		case GET_EJECT_STATUS_SR1_MOUNTED:
			return ExecResult.builder().exitValue(0)
					.stdOut(Arrays.asList("eject: `/dev/sr1' is mounted at `/media/dvd1'")).build();

		case GET_EJECT_STATUS_SR2_MOUNTED:
			return ExecResult.builder().exitValue(0)
					.stdOut(Arrays.asList("eject: `/dev/sr2' is mounted at `/media/dvd2'")).build();

		case GET_EJECT_STATUS_SR3_MOUNTED:
			return ExecResult.builder().exitValue(0)
					.stdOut(Arrays.asList("eject: `/dev/sr3' is mounted at `/media/dvd3'")).build();

		default:
			log.error("unknown command: " + command);
			return ExecResult.builder().exitValue(1).stdOut(Arrays.asList("unknown command: " + command)).build();
		}
	}

}
