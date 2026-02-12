package com.rupesh.ticket_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private ResponseEntity<ErrorResponse> build(HttpStatus status, String error, String message,
			HttpServletRequest request) {
		return ResponseEntity.status(status)
				.body(new ErrorResponse(status.value(), error, message, request.getRequestURI()));
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex,
			HttpServletRequest request) {
		ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Invalid request body", ex.getMessage(),
				request.getRequestURI());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex,
			HttpServletRequest request) {
		return build(HttpStatus.BAD_REQUEST, "Invalid Parameter", ex.getName(), request);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
			HttpServletRequest request) {
		return build(HttpStatus.BAD_REQUEST, "Wrong Input ", ex.getMessage(), request);

	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex,
			HttpServletRequest request) {
		return build(HttpStatus.NOT_FOUND, "Resource not found", ex.getMessage(), request);

	}

	@ExceptionHandler(TickerNotFoundException.class)
	public ResponseEntity<Object> handleTickerNotFoundException(TickerNotFoundException ex,
			HttpServletRequest request) {
		ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Resource not found", ex.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

	}

	@ExceptionHandler(DuplicateEntryException.class)
	public ResponseEntity<Object> handleDuplicateEntryException(DuplicateEntryException ex,
			HttpServletRequest request) {
		ErrorResponse error = new ErrorResponse(HttpStatus.CONFLICT.value(), "Resource already present",
				ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);

	}

	@ExceptionHandler(NotAllowedException.class)
	public ResponseEntity<Object> handleNotAllowedException(NotAllowedException ex, HttpServletRequest request) {
		ErrorResponse error = new ErrorResponse(HttpStatus.FORBIDDEN.value(), "Forbidden Action", ex.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);

	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
		ErrorResponse error = new ErrorResponse(HttpStatus.FORBIDDEN.value(), "Forbidden Action", ex.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);

	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<Object> handleMissingServletRequestParameterException(
			MissingServletRequestParameterException ex, HttpServletRequest request) {
		ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Missing Parameter", ex.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGeneric(Exception ex, HttpServletRequest request) {

		return build(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error", ex.getMessage(), request);

	}
}
