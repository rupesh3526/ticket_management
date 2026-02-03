package com.rupesh.ticket_management.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex,
			HttpServletRequest request) {
		ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Invalid request body", ex.getMessage(),
				request.getRequestURI());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
			HttpServletRequest request) {
		ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Wrong Input", ex.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

	}
	//Removed because this NoSuchElementException can be thrown by many reasons not for only resources not find cases.
	/*
	 * @ExceptionHandler(NoSuchElementException.class) public ResponseEntity<Object>
	 * handleNoSuchElementException(NoSuchElementException ex,HttpServletRequest
	 * request) { ErrorResponse error = new ErrorResponse(
	 * HttpStatus.NOT_FOUND.value(),
	 * "What you were trying to get not avaliable in Database", ex.getMessage(),
	 * request.getRequestURI()); return
	 * ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	 * 
	 * }
	 */
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex,
			HttpServletRequest request) {
		ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Resource not found", ex.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

	}
	
	@ExceptionHandler(TickerNotFoundException.class)
	public ResponseEntity<Object> handleTickerNotFoundException(TickerNotFoundException ex,
			HttpServletRequest request) {
		ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Resource not found", ex.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

	}
	
	@ExceptionHandler(DplicateEntryException.class)
	public ResponseEntity<Object> handleDplicateEntryException(DplicateEntryException ex,
			HttpServletRequest request) {
		ErrorResponse error = new ErrorResponse(HttpStatus.CONFLICT.value(), "Resource already present", ex.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);

	}
	
	
	
	
	

}
