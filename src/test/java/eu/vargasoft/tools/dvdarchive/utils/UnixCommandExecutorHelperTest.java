package eu.vargasoft.tools.dvdarchive.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import eu.vargasoft.tools.dvdarchive.model.ExecResult;

public class UnixCommandExecutorHelperTest {

	@Test
	public void testGetExactMountPointsPositive() throws IOException {
		List<String> stdOut = new ArrayList<>();
		stdOut.add("  logical name: /dev/sr0");
		stdOut.add("  logical name: /dev/sr1");
		stdOut.add("  logical name: /dev/sr0");
		stdOut.add("  logical name: /dev/sr2");

		ExecResult commandResult = ExecResult.builder().stdOut(stdOut).exitValue(0).stdErr(null).build();
		Set<String> result = UnixCommandExecutorHelper.getExactMountPoints(commandResult);

		assertNotNull(result);
		assertEquals(3, result.size());
		assertTrue(result.contains("/dev/sr0"));
		assertTrue(result.contains("/dev/sr1"));
		assertTrue(result.contains("/dev/sr2"));

	}

	@Test(expected = InvalidParameterException.class)
	public void testGetExactMountPointsNegative() throws IOException {
		List<String> stdOut = new ArrayList<>();
		stdOut.add("  logical name /dev/sx0");
		stdOut.add("  logical name /dev/sx1");
		stdOut.add("  logical name /dev/sx0");
		stdOut.add("  logical name /dev/sx2");

		ExecResult commandResult = ExecResult.builder().stdOut(stdOut).exitValue(0).stdErr(null).build();
		Set<String> result = UnixCommandExecutorHelper.getExactMountPoints(commandResult);

		assertNotNull(result);
		assertEquals(0, result.size());

	}
}
