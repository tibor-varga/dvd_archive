/**
 * 
 */
package eu.vargasoft.tools.dvdarchive.model;

import eu.vargasoft.tools.dvdarchive.utils.UnixCommands;

/**
 * @author buxi
 *
 */
public class UdfCopyUnixCommand implements DiscCopyUnixCommand {

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.vargasoft.tools.dvdarchive.DiscCopyUnixCommand#getUnixCommand()
	 */
	@Override
	public String getUnixCommand(final Disc disc, final String destination) {
		return String.format(UnixCommands.DD, disc.getMountPoint(), destination);
	}
}
