package com.rupesh.ticket_management.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class globalExceptionHandler {
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Object>  handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object>  handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		
	}
	
	@ExceptionHandler(NoSuchElementException .class)
	public ResponseEntity<Object>  handleNoSuchElementException (NoSuchElementException  ex) {
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		
	}
	
	

}
