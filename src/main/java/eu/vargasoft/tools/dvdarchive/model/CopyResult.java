/**
 * 
 */
package eu.vargasoft.tools.dvdarchive.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author buxi
 *
 */
@Setter
@Getter
@Builder
public class CopyResult {
	ExecResult execResult;
	Disc disc;
}
