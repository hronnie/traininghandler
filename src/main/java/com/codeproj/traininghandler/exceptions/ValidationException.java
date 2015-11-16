package com.codeproj.traininghandler.exceptions;

import org.apache.log4j.Logger;

public class ValidationException extends Exception {

	private static final Logger logger = Logger.getLogger(ValidationException.class);
	
	private static final long serialVersionUID = 1L;

	public ValidationException() {
		super();
		logger.info("Validation exception has been thrown");
	}

	public ValidationException(String message) {
		super(message);
		logger.info("Validation exception has been thrown with the following message>> " + message);
		
	}
	
	public ValidationException(String message, Throwable cause) {
		super(message, cause);
		logger.info("Validation exception has been thrown with the following message>> " + message);
	}
	
	public ValidationException(Throwable cause) {
		super(cause);
		logger.info("Validation exception has been thrown");
	}
}

