package eu.vargasoft.tools.dvdarchive.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import eu.vargasoft.tools.dvdarchive.utils.ExecResult.ExecResultBuilder;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UnixCommandExecutor {
	public ExecResult execute(String command, String stdoutFilter) throws IOException {
		String s = null;
		Process p = Runtime.getRuntime().exec(command);
		ExecResultBuilder builder = ExecResult.builder();

		try (BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
				BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));) {

			// Compile regex as predicate
			Predicate<String> filter = Pattern.compile(stdoutFilter).asPredicate();
			// read the output from the command
			List<String> filteredStdout = stdInput.lines().filter(filter).collect(Collectors.<String>toList());

			builder.stdOut(filteredStdout);
			log.debug("Here is the standard output of the command: {}", filteredStdout);

			// read any errors from the attempted command
			StringBuffer stdErr = new StringBuffer();
			while ((s = stdError.readLine()) != null) {
				stdErr.append(s);
			}
			builder.stdErr(stdErr.toString());
			log.debug("Here is the standard error of the command: {}", stdErr.toString());
		} catch (IOException e) {
			log.error("error occured: {}", e);
		}
		builder.exitValue(p.exitValue());
		return builder.build();
	}

	public static Set<String> getExactMountPoints(ExecResult commandResult) {
		// TODO with java 8
		Set<String> result = new HashSet<String>();
		for (String string : commandResult.getStdOut()) {
			String[] splits = string.split(":");
			result.add(splits[1].trim());
		}
		return result;
	}

}
