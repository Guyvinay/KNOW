package com.know.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> globalExceptionHandler(Exception ex, WebRequest wb){
		return new ResponseEntity<ErrorDetails>(
				new ErrorDetails(
						LocalDateTime.now(), 
						ex.getMessage(), 
						wb.getDescription(false)
						), 
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<ErrorDetails> StudentExceptionHandler(StudentNotFoundException ex, WebRequest wb){
		return new ResponseEntity<ErrorDetails>(
				new ErrorDetails(
						LocalDateTime.now(), 
						ex.getMessage(), 
						wb.getDescription(false)
						), 
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(FacultyNotFoundException.class)
	public ResponseEntity<ErrorDetails> facultyExceptionHandler(FacultyNotFoundException ex, WebRequest wb){
		return new ResponseEntity<ErrorDetails>(
				new ErrorDetails(
						LocalDateTime.now(), 
						ex.getMessage(), 
						wb.getDescription(false)
						), 
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CoachingNotFoundException.class)
	public ResponseEntity<ErrorDetails> coachingExceptionHandler(CoachingNotFoundException ex, WebRequest wb){
		return new ResponseEntity<ErrorDetails>(
				new ErrorDetails(
						LocalDateTime.now(), 
						ex.getMessage(), 
						wb.getDescription(false)
						), 
				HttpStatus.NOT_FOUND);
	}
	
	
}
