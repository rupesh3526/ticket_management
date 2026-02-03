package com.rupesh.ticket_management.exception;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ErrorResponse {
	private int status;
	
	private String error;
	private String message;
	private String path;
	private LocalDateTime time;

	ErrorResponse(int status ,String error, String message, String path) {
		this.status=status;
		this.error=error;
		this.message=message;
		this.path=path;
		this.time= LocalDateTime.now();
	}

}
