package com.know.exception;

public class CoachingNotException extends RuntimeException {

	public CoachingNotException(String msg) {
		super(msg);
	}
	public CoachingNotException() {
		super("Coaching Not Found!!!");
	}
	
}
