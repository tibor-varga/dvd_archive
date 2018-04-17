/**
 * 
 */
package eu.vargasoft.tools.dvdarchive;

import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * @author buxi
 *
 */
@org.springframework.boot.context.properties.ConfigurationProperties(prefix = "app")
@Configuration
@Data
public class ConfigurationProperties {
	private String mainTargetDir;
}
