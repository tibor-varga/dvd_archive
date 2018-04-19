/**
 * 
 */
package eu.vargasoft.tools.dvdarchive.utils;

/**
 * @author buxi
 *
 */
public final class UnixCommands {
	public static final String LSHW = "/usr/bin/lshw -C disk";
	public static final String BLKID = "/sbin/blkid %s";
	public static final String EJECT = "/usr/bin/eject %s";
	public static final String EJECT_CLOSE = "/usr/bin/eject -t %s";
	public static final String EJECT_STATUS = "/usr/bin/eject -n -v %s";
	public static final String MKDIR = "/bin/mkdir %s";
	public static final String DD = "/bin/dd if=%s of=%s/%s.iso";
	public static final String CP = "/usr/bin/rsync -avq %s/ %s/";

	private UnixCommands() {
		throw new IllegalStateException("Utility class");
	}
}
