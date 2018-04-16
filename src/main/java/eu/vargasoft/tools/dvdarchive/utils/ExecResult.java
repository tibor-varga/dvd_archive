package eu.vargasoft.tools.dvdarchive.utils;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ExecResult {
	private List<String>stdOut;
	private String stdErr;
	private long exitValue;
}
