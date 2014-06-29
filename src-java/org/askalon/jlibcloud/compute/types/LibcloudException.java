package org.askalon.jlibcloud.compute.types;

import java.io.File;
import java.util.List;

public class LibcloudException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1096692961148803341L;

	private String exceptionType;
	private String exceptionValue;
	private List<List<String>> traceback;

	public LibcloudException(String exceptionType, String exceptionValue,
			List<List<String>> traceback) {
		super(exceptionType+": "+exceptionValue);
		this.exceptionType = exceptionType;
		this.exceptionValue = exceptionValue;
		this.traceback = traceback;
		setStackTrace(traceback);
	}

	private void setStackTrace(List<List<String>> traceback) {
		int size = traceback.size();
		if (size > 0) {
			StackTraceElement[] stackTrace = new StackTraceElement[size];
			for (int i = 0; i < size; i++) {
				List<String> elem = traceback.get(i);
				File f = new File(elem.get(0));
				String pseudoClassName = f.getName().replaceAll(".py", "");
				// "ClassName","methodName","fileName",lineNumber
				stackTrace[i] = new StackTraceElement(pseudoClassName, elem.get(2),
						elem.get(0), Integer.valueOf(elem.get(1)));
			}
			this.setStackTrace(stackTrace);
		}
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getExceptionType() {
		return exceptionType;
	}

	public String getExceptionValue() {
		return exceptionValue;
	}

	public List<List<String>> getTraceback() {
		return traceback;
	}
}
