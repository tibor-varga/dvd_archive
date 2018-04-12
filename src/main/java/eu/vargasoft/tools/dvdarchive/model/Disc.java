/**
 * 
 */
package eu.vargasoft.tools.dvdarchive.model;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class Disc {
	private final String mountPoint;
	private final DiskType type;
}
