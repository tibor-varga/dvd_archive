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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { TestConfig.class, DiscController.class})
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
	public void testGetDisc() throws IOException {
		Disc disc = discController.getDisc(UnixCommandExecutorMock.DEVSR0);
		assertNotNull(disc);
		assertEquals(UnixCommandExecutorMock.DEVSR0, disc.getMountPoint());
		assertEquals("label1", disc.getLabel());
		assertEquals(DiscType.ISO9660, disc.getType());
	}
}
