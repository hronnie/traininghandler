package com.codeproj.traininghandler.exceptions;

public class DatabaseEntityNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public DatabaseEntityNotFoundException() {
		super();
	}

	public DatabaseEntityNotFoundException(String message) {
		super(message);
	}
	
	public DatabaseEntityNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DatabaseEntityNotFoundException(Throwable cause) {
		super(cause);
	}
}
