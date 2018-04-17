package eu.vargasoft.tools.dvdarchive;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import eu.vargasoft.tools.dvdarchive.utils.UnixCommandExecutorInterface;
import eu.vargasoft.tools.dvdarchive.utils.UnixCommandExecutorMock;

@Configuration
@ComponentScan
@Profile("dev")
public class TestConfig {
	@Bean
	public UnixCommandExecutorInterface commandExecutor() {
		return new UnixCommandExecutorMock();
	}
}
