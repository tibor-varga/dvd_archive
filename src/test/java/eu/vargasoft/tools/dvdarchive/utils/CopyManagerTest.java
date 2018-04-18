package eu.vargasoft.tools.dvdarchive.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import eu.vargasoft.tools.dvdarchive.model.Disc;
import eu.vargasoft.tools.dvdarchive.model.DiscType;
import eu.vargasoft.tools.dvdarchive.model.ExecResult;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class CopyManagerTest {

	@Autowired
	private CopyManager copyManager;

	@Test
	public void testCopySr0() throws IOException, InterruptedException {
		CopyResult result = copyManager.copyDisk(UnixCommandExecutorMock.DEVSR1);
		Disc disc = checkCopyResult(result);

		assertEquals(UnixCommandExecutorMock.MEDIA_DVD1, disc.getTrayInfo().getDirectory());
	}

	private Disc checkCopyResult(CopyResult result) {
		assertNotNull(result);
		Disc disc = result.getDisc();
		assertNotNull(disc);
		ExecResult execResult = result.getExecResult();
		assertNotNull(execResult);
		if (disc.getType() == DiscType.UDF) {
			assertTrue(execResult.getStdOut().get(0).contains("dd "));
			assertTrue(execResult.getStdOut().get(0).contains("sr"));
		} else if (disc.getType() == DiscType.ISO9660) {
			assertTrue(execResult.getStdOut().get(0).contains("cp "));
			assertTrue(execResult.getStdOut().get(0).contains("media"));
		}
		return disc;
	}

	@Test
	public void testCopyAllDisk() throws IOException, InterruptedException {
		HashMap<String, CopyResult> result = copyManager.copyAllDiscs();
		assertNotNull(result);
		assertEquals(4, result.size());

		for (CopyResult copyResult : result.values()) {
			if (copyResult != null) {
				checkCopyResult(copyResult);
			}
		}
	}
}
