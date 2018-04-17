package eu.vargasoft.tools.dvdarchive.utils;

import java.io.IOException;

import eu.vargasoft.tools.dvdarchive.model.ExecResult;

public interface UnixCommandExecutorInterface {

	ExecResult execute(String command, String stdoutFilter) throws IOException;

}