package eu.vargasoft.tools.dvdarchive;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import eu.vargasoft.tools.dvdarchive.model.Disc;
import eu.vargasoft.tools.dvdarchive.model.DiscType;
import eu.vargasoft.tools.dvdarchive.model.TrayStatus;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { TestConfig.class,
		DiscController.class })
@ActiveProfiles("dev")
public class DiscControllerTest {

	@Autowired
	private DiscController discController;

	@Test
	public void testGetMountPoints() throws IOException {
		Set<String> result = discController.getMountPoints();
		assertNotNull(result);
		assertTrue(result.contains(UnixCommandExecutorMock.DEVSR0));
		assertTrue(result.contains(UnixCommandExecutorMock.DEVSR1));
		assertTrue(result.contains(UnixCommandExecutorMock.DEVSR2));
	}

	@Test
	public void testGetDisc0() throws IOException {
		Disc disc = discController.getDisc(UnixCommandExecutorMock.DEVSR0);
		assertNotNull(disc);
		assertEquals(UnixCommandExecutorMock.DEVSR0, disc.getMountPoint());
		assertEquals("label0", disc.getLabel());
		assertEquals(DiscType.ISO9660, disc.getType());
	}

	@Test
	public void testGetDisc1() throws IOException {
		Disc disc = discController.getDisc(UnixCommandExecutorMock.DEVSR1);
		assertNotNull(disc);
		assertEquals(UnixCommandExecutorMock.DEVSR1, disc.getMountPoint());
		assertEquals("label1", disc.getLabel());
		assertEquals(DiscType.UDF, disc.getType());
	}

	@Test
	public void testGetDisc2() throws IOException {
		Disc disc = discController.getDisc(UnixCommandExecutorMock.DEVSR2);
		assertNotNull(disc);
		assertEquals(UnixCommandExecutorMock.DEVSR2, disc.getMountPoint());
		assertEquals("label2", disc.getLabel());
		assertEquals(DiscType.UDF, disc.getType());
	}

	@Test
	public void testGetDisc3() throws IOException {
		Disc disc = discController.getDisc(UnixCommandExecutorMock.DEVSR3);
		assertNotNull(disc);
		assertEquals(UnixCommandExecutorMock.DEVSR3, disc.getMountPoint());
		assertEquals("label3", disc.getLabel());
		assertEquals(DiscType.ISO9660, disc.getType());
	}

	@Test
	public void testGetTrayStatusNotMounted() throws IOException {
		TrayStatus status = discController.getTrayStatus(UnixCommandExecutorMock.DEVSR0);
		assertNotNull(status);
		assertEquals(TrayStatus.NOT_MOUNTED, status);
	}

	@Test
	public void testGetTrayStatusMounted() throws IOException {
		TrayStatus status = discController.getTrayStatus(UnixCommandExecutorMock.DEVSR1);
		assertNotNull(status);
		assertEquals(TrayStatus.MOUNTED, status);
	}
}
