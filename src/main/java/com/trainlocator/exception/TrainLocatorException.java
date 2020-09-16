package com.trainlocator.exception;

public class TrainLocatorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TrainLocatorException() {
		super();
	}

	public TrainLocatorException(String message, Throwable cause) {
		super(message, cause);
	}

}
