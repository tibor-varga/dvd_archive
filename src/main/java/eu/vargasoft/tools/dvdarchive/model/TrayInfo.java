/**
 * 
 */
package eu.vargasoft.tools.dvdarchive.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author buxi
 *
 */

@Data
@Builder
public class TrayInfo {
	public final TrayStatus status;
	public final String directory;
}
