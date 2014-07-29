package com.codeproj.traininghandler.exceptions;


public class GeneralException extends Exception {
	
	private static final long serialVersionUID = -8513108449365813032L;

	public GeneralException() {
		super();
	}

	public GeneralException(String message) {
		super(message);
	}
	
	public GeneralException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public GeneralException(Throwable cause) {
		super(cause);
	}
}
