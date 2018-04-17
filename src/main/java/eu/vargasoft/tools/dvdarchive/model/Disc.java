/**
 * 
 */
package eu.vargasoft.tools.dvdarchive.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Base class for optic drives
 * 
 * @author buxi
 *
 */
@Getter
@Setter
@Builder
public class Disc {
	private final String mountPoint;
	private final DiscType type;
	private final String label;
	private final TrayInfo trayInfo;
}
