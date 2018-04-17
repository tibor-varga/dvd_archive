package eu.vargasoft.tools.dvdarchive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ToolsApplication {

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
	// TODO Taskboard Todo refactor mountPoint->drive, directory->mountPoint
	// TODO Taskboard Todo Jenkins onto rechner
	// TODO Taskboard Todo activate Sonarqube
	// TODO Taskboard Todo IT tests witc real dvds
	// TODO Taskboard Todo implementation of Audo CD Support
	// TODO Taskboard Todo parameters from properties
	// TODO Taskboard Todo parallelizatin of copy tasks
	// TODO Taskboard Todo Profiles in Spring Boot Start missing

}
