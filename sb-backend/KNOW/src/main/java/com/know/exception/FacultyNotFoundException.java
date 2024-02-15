package com.know.exception;

public class FacultyNotFoundException extends RuntimeException {

	public FacultyNotFoundException(String msg) {
		super(msg);
	}
	public FacultyNotFoundException() {
		super("Faculty Not Found!!!");
	}
	
}
