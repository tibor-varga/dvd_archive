package eu.vargasoft.tools.dvdarchive;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import eu.vargasoft.tools.dvdarchive.model.DiscCopyUnixCommand;
import eu.vargasoft.tools.dvdarchive.model.DiscType;
import eu.vargasoft.tools.dvdarchive.model.Iso9660CopyUnixCommand;
import eu.vargasoft.tools.dvdarchive.model.UdfCopyUnixCommand;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class DiscCopyUnixCommandFactoryTest {

	@Test
	public void testCreateUnixCommandUdf() {
		DiscCopyUnixCommand result = DiscCopyUnixCommandFactory.createUnixCommand(DiscType.UDF);
		assertNotNull(result);
		assertEquals(UdfCopyUnixCommand.class, result.getClass());
	}

	@Test
	public void testCreateUnixCommandIso9660() {
		DiscCopyUnixCommand result = DiscCopyUnixCommandFactory.createUnixCommand(DiscType.ISO9660);
		assertNotNull(result);
		assertEquals(Iso9660CopyUnixCommand.class, result.getClass());
	}

}
