package com.know.exception;

public class CoachingNotFoundException extends RuntimeException {

	public CoachingNotFoundException(String msg) {
		super(msg);
	}
	public CoachingNotFoundException() {
		super("Coaching Not Found!!!");
	}
	
}
