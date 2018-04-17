package eu.vargasoft.tools.dvdarchive.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class ExecResult {
	private List<String> stdOut;
	private String stdErr;
	private long exitValue;
}
