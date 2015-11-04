package com.codeproj.traininghandler.exceptions;

import org.apache.log4j.Logger;


public class GeneralException extends Exception {
	
	private static final Logger logger = Logger.getLogger(GeneralException.class);
	
	private static final long serialVersionUID = -8513108449365813032L;

	public GeneralException() {
		super();
		logger.info("GeneralException exception has been thrown");
	}

	public GeneralException(String message) {
		super(message);
		logger.info("GeneralException exception has been thrown with the following message>> " + message);
	}
	
	public GeneralException(String message, Throwable cause) {
		super(message, cause);
		logger.info("GeneralException exception has been thrown with the following message>> " + message);
	}
	
	public GeneralException(Throwable cause) {
		super(cause);
		logger.info("GeneralException exception has been thrown");
	}
}
