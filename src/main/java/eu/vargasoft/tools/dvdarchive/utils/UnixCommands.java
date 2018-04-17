/**
 * 
 */
package eu.vargasoft.tools.dvdarchive.utils;

/**
 * @author buxi
 *
 */
public final class UnixCommands {
	public final static String LSHW = "/usr/bin/lshw -C disk";
	public final static String BLKID = "/sbin/blkid ";
	public static final String EJECT = "/usr/bin/eject ";
	public static final String EJECT_CLOSE = "/usr/bin/eject -t ";
	public static final String EJECT_STATUS = "/usr/bin/eject -n -v ";
}
