/**
 * 
 */
package eu.vargasoft.tools.dvdarchive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author buxi
 *
 */
@Component
@Slf4j
public class DiscController {
	List<String> getMountPoints() throws IOException {
		ArrayList<String> result = new ArrayList<String>();
		String cmd = "lshw -C disk | grep sr | cut -d \":\" -f 2 | sort -u";
		String s = null;

		Process p = Runtime.getRuntime().exec(cmd);

		try (BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
				BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));) {

			// read the output from the command
			StringBuffer stdOut = new StringBuffer();
			while ((s = stdInput.readLine()) != null) {
				stdOut.append(s);
			}
			log.info("Here is the standard output of the command: {}", stdOut.toString());

			// read any errors from the attempted command
			StringBuffer stdErr = new StringBuffer();
			while ((s = stdError.readLine()) != null) {
				stdErr.append(s);
			}
			log.info("Here is the standard error of the command: {}", stdErr.toString());
		} catch (IOException e) {
			log.error("error occured: {}", e);
		}

		return result;
	}
}
