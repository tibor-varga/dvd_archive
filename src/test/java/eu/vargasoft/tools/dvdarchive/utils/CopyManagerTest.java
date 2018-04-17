package eu.vargasoft.tools.dvdarchive.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import eu.vargasoft.tools.dvdarchive.CopyManager;
import eu.vargasoft.tools.dvdarchive.model.CopyResult;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class CopyManagerTest {

	@Autowired
	private CopyManager copyManager;

	@Test
	public void testCopySr0() throws IOException {
		CopyResult result = copyManager.copyDisk(UnixCommandExecutorMock.DEVSR1);
		assertNotNull(result);
		assertNotNull(result.getDisc());
		assertNotNull(result.getExecResult());

		assertEquals(UnixCommandExecutorMock.MEDIA_DVD1, result.getDisc().getTrayInfo().getDirectory());
	}

	@Test
	public void testCopyAllDisk() throws IOException {
		HashMap<String, CopyResult> result = copyManager.copyAllDiscs();
		assertNotNull(result);
		assertEquals(4, result.size());
	}
}
