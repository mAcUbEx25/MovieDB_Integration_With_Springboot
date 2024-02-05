package dev.codescreen.handler;

import java.util.UUID;

/**
 * Custom exception for InternServerException.
 */
public class MediaDbInternalServerException extends Exception {

	private static final long serialVersionUID = 545324525736110709L;

	private String errorId = UUID.randomUUID().toString();

	public static final String INTERNAL_SERVER_ERROR_MSG = "An internal server error occured while handling your request. Please check the logs.";

	private Throwable rootCause;

	public MediaDbInternalServerException(Throwable cause) {
		super(cause);
		this.rootCause = cause;
	}

	public String getErrorId() {
		return errorId;
	}

	public Throwable getRootCause() {
		return rootCause;
	}

}
