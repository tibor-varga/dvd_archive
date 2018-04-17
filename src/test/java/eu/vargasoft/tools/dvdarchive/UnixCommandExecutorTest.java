package eu.vargasoft.tools.dvdarchive;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import eu.vargasoft.tools.dvdarchive.model.ExecResult;
import eu.vargasoft.tools.dvdarchive.utils.UnixCommandExecutor;
import eu.vargasoft.tools.dvdarchive.utils.UnixCommandExecutorInterface;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class UnixCommandExecutorTest {

	@Test
	public void testExecute() throws IOException {
		UnixCommandExecutorInterface commandExecutor = new UnixCommandExecutor();

		String cmd = "lshw -C disk";
		ExecResult commandResult = commandExecutor.execute(cmd, "/dev/sr*");
		assertNotNull(commandResult);
		assertEquals(0, commandResult.getExitValue());
	}
}
