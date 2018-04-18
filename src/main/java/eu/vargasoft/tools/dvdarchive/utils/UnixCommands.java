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
	public final static String BLKID = "/sbin/blkid %s";
	public static final String EJECT = "/usr/bin/eject %s";
	public static final String EJECT_CLOSE = "/usr/bin/eject -t %s";
	public static final String EJECT_STATUS = "/usr/bin/eject -n -v %s";
	public static final String MKDIR = "/bin/mkdir %s";
	public static final String DD = "/bin/dd if=%s of=%s";
	public static final String CP = "/bin/cp -r %s/* %s/";
}
