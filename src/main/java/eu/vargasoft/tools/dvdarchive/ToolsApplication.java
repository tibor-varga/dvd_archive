package eu.vargasoft.tools.dvdarchive;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import eu.vargasoft.tools.dvdarchive.model.CopyResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication()
public class ToolsApplication implements CommandLineRunner {

	@Autowired
	CopyManager copyManager;

	public static void main(String[] args) {
		SpringApplication.run(ToolsApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		Map<String, CopyResult> result = copyManager.copyAllDiscs();
		log.info("result of all disc copy: {}", result);
	}

}
