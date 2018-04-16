/**
 * 
 */
package eu.vargasoft.tools.dvdarchive;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Arrays;

import eu.vargasoft.tools.dvdarchive.utils.ExecResult;
import eu.vargasoft.tools.dvdarchive.utils.UnixCommandExecutorInterface;
import eu.vargasoft.tools.dvdarchive.utils.UnixCommands;

/**
 * @author buxi
 *
 */
public class UnixCommandExecutorMock implements UnixCommandExecutorInterface {
	public final static String DEVSR0 = "/dev/sr0";
	public final static String DEVSR1 = "/dev/sr1";
	public final static String DEVSR2 = "/dev/sr2";
	public final static String DEVSR3 = "/dev/sr3";
	public final static String GETDISC_SR0 = UnixCommands.BLKID + DEVSR0;

	@Override
	public ExecResult execute(String command, String stdoutFilter) throws IOException {
		switch (command) {
		case GETDISC_SR0:
			return ExecResult.builder().exitValue(0)
					.stdOut(Arrays
							.asList("/dev/sr0: UUID=\"2007-08-17-20-51-00-00\" LABEL=\"label1\" TYPE=\"iso9660\""))
					.build();
		case UnixCommands.LSHW:
			return ExecResult.builder().exitValue(0)
					.stdOut(Arrays.asList("logical name: /dev/sr0", " 	  logical name: /dev/sr1",
							"     logical name: /dev/sr2", "logical name: /dev/sr3", "logical name: /dev/sr3"))
					.build();
		default:
			throw new InvalidParameterException("unknown command: " + command);
		}
	}

}