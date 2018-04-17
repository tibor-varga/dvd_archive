package eu.vargasoft.tools.dvdarchive;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import eu.vargasoft.tools.dvdarchive.utils.UnixCommandExecutorInterface;
import eu.vargasoft.tools.dvdarchive.utils.UnixCommandExecutorMock;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@ComponentScan
@Profile("dev")
public class TestConfig {
	@Bean
	public UnixCommandExecutorInterface commandExecutor() {
		log.info("TestConfig loaded");
		return new UnixCommandExecutorMock();
	}
}
