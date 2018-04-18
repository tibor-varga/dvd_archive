/**
 * 
 */
package eu.vargasoft.tools.dvdarchive.model;

/**
 * @author buxi
 *
 */
public interface DiscCopyUnixCommand {
	public String getUnixCommand(final Disc disc, final String destination);
}
