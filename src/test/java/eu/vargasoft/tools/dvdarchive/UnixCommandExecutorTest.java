package eu.vargasoft.tools.dvdarchive;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;

import eu.vargasoft.tools.dvdarchive.utils.ExecResult;
import eu.vargasoft.tools.dvdarchive.utils.UnixCommandExecutor;

public class UnixCommandExecutorTest {

	@Test
	public void testExecute() throws IOException {
		UnixCommandExecutor commandExecutor = new UnixCommandExecutor();

		String cmd = "lshw -C disk";
		ExecResult commandResult = commandExecutor.execute(cmd, "/dev/sr*");
		assertNotNull(commandResult);
		assertEquals(0, commandResult.getExitValue());
	}
}
