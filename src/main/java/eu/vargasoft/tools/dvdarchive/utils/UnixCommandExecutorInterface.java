package eu.vargasoft.tools.dvdarchive.utils;

import java.io.IOException;

public interface UnixCommandExecutorInterface {

	ExecResult execute(String command, String stdoutFilter) throws IOException;

}