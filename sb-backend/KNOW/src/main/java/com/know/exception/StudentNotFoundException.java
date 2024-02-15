package com.know.exception;

public class StudentNotFoundException extends RuntimeException {

	public StudentNotFoundException(String msg) {
		super(msg);
	}
	public StudentNotFoundException() {
		super("Student Not Found!!!");
	} 

}
