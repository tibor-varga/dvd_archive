/**
 * 
 */
package eu.vargasoft.tools.dvdarchive.utils;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

import eu.vargasoft.tools.dvdarchive.model.ExecResult;

/**
 * @author buxi
 *
 */
public class UnixCommandExecutorHelper {

	/**
	 * extracting distinct mount point information from stdout<br>
	 * <br>
	 * <code> sudo lshw -C disk | grep sr<br>
	 * 	  logical name: /dev/sr0<br>
	 * 	  logical name: /dev/sr1<br>
	 * 	  logical name: /dev/sr2<br>
	 * 	  logical name: /dev/sr3<br>
	 </code>
	 * 
	 * @param commandResult
	 *            containing stdout, stderr
	 * @return set of extracted mount points
	 */
	public static Set<String> getExactMountPoints(ExecResult commandResult) {
		// TODO with java 8
		Set<String> result = new HashSet<String>();
		for (String string : commandResult.getStdOut()) {
			String[] splits = string.split(":");
			if (splits.length < 2) {
				throw new InvalidParameterException(": expected");
			}
			result.add(splits[1].trim());
		}
		return result;
	}

}
