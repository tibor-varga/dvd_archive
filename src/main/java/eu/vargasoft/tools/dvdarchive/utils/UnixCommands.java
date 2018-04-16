/**
 * 
 */
package eu.vargasoft.tools.dvdarchive.utils;

/**
 * @author buxi
 *
 */
public final class UnixCommands {
	public final static String LSHW = "lshw -C disk";
	public final static String BLKID = "blkid ";
	public static final String EJECT = "eject ";
	public static final String EJECT_CLOSE = "eject -t ";
	public static final String EJECT_STATUS = "eject -n -v ";
}
