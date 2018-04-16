/**
 * 
 */
package eu.vargasoft.tools.dvdarchive.model;

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
	public String getUnixCommand(String source, String destination) {
		return "dd if=" + source + " of=" + destination;
	}
}
