package eu.vargasoft.tools.dvdarchive;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import eu.vargasoft.tools.dvdarchive.utils.UnixCommandExecutorInterface;

@Configuration
@Profile("dev")
public class TestConfig {
	@Bean
	public UnixCommandExecutorInterface commandExecutor() {
		return new UnixCommandExecutorMock();
	}
}
