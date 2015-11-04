package com.codeproj.traininghandler.exceptions;

import org.apache.log4j.Logger;

public class DatabaseEntityNotFoundException extends Exception {
	
	private static final Logger logger = Logger.getLogger(DatabaseEntityNotFoundException.class);

	private static final long serialVersionUID = 1L;

	public DatabaseEntityNotFoundException() {
		super();
		logger.info("DatabaseEntityNotFoundException exception has been thrown");
	}

	public DatabaseEntityNotFoundException(String message) {
		super(message);
		logger.info("DatabaseEntityNotFoundException exception has been thrown with the following message>> " + message);
	}
	
	public DatabaseEntityNotFoundException(String message, Throwable cause) {
		super(message, cause);
		logger.info("DatabaseEntityNotFoundException exception has been thrown with the following message>> " + message);
	}
	
	public DatabaseEntityNotFoundException(Throwable cause) {
		super(cause);
		logger.info("DatabaseEntityNotFoundException exception has been thrown");
	}
}
