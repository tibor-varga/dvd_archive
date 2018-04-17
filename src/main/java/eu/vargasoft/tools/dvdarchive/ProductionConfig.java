package eu.vargasoft.tools.dvdarchive;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import eu.vargasoft.tools.dvdarchive.utils.UnixCommandExecutor;
import eu.vargasoft.tools.dvdarchive.utils.UnixCommandExecutorInterface;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@Profile("production")
public class ProductionConfig {
	@Bean
	public UnixCommandExecutorInterface commandExecutor() {
		log.info("ProductionConfig loaded");
		return new UnixCommandExecutor();
	}
}
