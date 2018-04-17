package eu.vargasoft.tools.dvdarchive.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import eu.vargasoft.tools.dvdarchive.model.ExecResult;
import eu.vargasoft.tools.dvdarchive.model.ExecResult.ExecResultBuilder;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UnixCommandExecutor implements UnixCommandExecutorInterface {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * eu.vargasoft.tools.dvdarchive.utils.UnixCommandExecutorInterface#execute(java
	 * .lang.String, java.lang.String)
	 */
	@Override
	public ExecResult execute(String command, String stdoutFilter) throws IOException {
		String s = null;
		Process p = Runtime.getRuntime().exec(command);
		ExecResultBuilder builder = ExecResult.builder();
		try (BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
				BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));) {
			List<String> filteredStdout;
			if (StringUtils.isEmpty(stdoutFilter)) {
				// unfiltered standard output
				filteredStdout = stdInput.lines().collect(Collectors.<String>toList());
			} else {
				// Compile regex as predicate
				Predicate<String> filter = Pattern.compile(stdoutFilter).asPredicate();
				// read the output from the command
				filteredStdout = stdInput.lines().filter(filter).collect(Collectors.<String>toList());
			}

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

}
