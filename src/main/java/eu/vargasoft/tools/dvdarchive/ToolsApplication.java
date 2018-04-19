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
	// TODO Taskboard ---------------------------------------------------------
	// TODO Taskboard DONE Profile introduce for UnixCommandExecutor versions
	// TODO Taskboard DONE get mount points
	// TODO Taskboard DONE get disc info
	// TODO Taskboard DONE command for open tray
	// TODO Taskboard DONE command for close tray
	// TODO Taskboard DONE copy commands for different type of dvds
	// TODO Taskboard DONE implementation of CopyManager
	// TODO Taskboard DONE Fix CopyManagerTest tests
	// TODO Taskboard DONE Jenkins onto rechner
	// TODO Taskboard DONE parameters from properties
	// TODO Taskboard DONE define target directories for cp and dd unix commands
	// TODO Taskboard DONE parallelizatin of copy tasks
	// TODO Taskboard DONE activate Sonarqube

	// TODO Taskboard Todo refactor mountPoint->drive, directory->mountPoint
	// TODO Taskboard Todo refactor Check System (unix commands, target dir, etc.)
	// TODO Taskboard Todo IT tests with real dvds
	// TODO Taskboard Todo implementation of Audo CD Support
	// TODO Taskboard Todo Profiles in Spring Boot Start missing

	@Override
	public void run(String... args) throws Exception {
		Map<String, CopyResult> result = copyManager.copyAllDiscs();
		log.info("result of all disc copy: {}", result);
	}

}
