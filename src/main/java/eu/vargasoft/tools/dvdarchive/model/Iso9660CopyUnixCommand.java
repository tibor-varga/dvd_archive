/**
 * 
 */
package eu.vargasoft.tools.dvdarchive.model;

import eu.vargasoft.tools.dvdarchive.utils.UnixCommands;

/**
 * @author buxi
 *
 */
public class Iso9660CopyUnixCommand implements DiscCopyUnixCommand {

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.vargasoft.tools.dvdarchive.DiscCopyUnixCommand#getUnixCommand()
	 */

	@Override
	public String getUnixCommand(Disc disc, String destination) {
		return String.format(UnixCommands.CP, disc.getTrayInfo().getDirectory(), destination);
	}
}
