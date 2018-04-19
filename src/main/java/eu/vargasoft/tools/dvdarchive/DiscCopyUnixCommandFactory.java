/**
 * 
 */
package eu.vargasoft.tools.dvdarchive;

import eu.vargasoft.tools.dvdarchive.model.DiscCopyUnixCommand;
import eu.vargasoft.tools.dvdarchive.model.DiscType;
import eu.vargasoft.tools.dvdarchive.model.Iso9660CopyUnixCommand;
import eu.vargasoft.tools.dvdarchive.model.UdfCopyUnixCommand;

/**
 * @author buxi
 *
 */
public class DiscCopyUnixCommandFactory {

	private DiscCopyUnixCommandFactory() {
		throw new IllegalStateException("Utility class");
	}

	public static DiscCopyUnixCommand createUnixCommand(DiscType discType) {
		switch (discType) {
		case UDF:
			return new UdfCopyUnixCommand();

		case ISO9660:
			return new Iso9660CopyUnixCommand();

		default:
			// impossible
			throw new IllegalStateException("Unknown discType: " + discType);
		}

	}
}
