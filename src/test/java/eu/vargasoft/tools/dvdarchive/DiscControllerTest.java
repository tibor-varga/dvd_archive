package eu.vargasoft.tools.dvdarchive;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

public class DiscControllerTest {

	@Test
	public void testGetMountPoints() throws IOException {
		DiscController discController = new DiscController();
		List<String> result = discController.getMountPoints();
		assertNotNull(result);
		assertEquals(0, result.size());
	}

}
