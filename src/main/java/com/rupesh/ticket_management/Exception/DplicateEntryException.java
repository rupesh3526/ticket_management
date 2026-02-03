package com.rupesh.ticket_management.exception;

public class DplicateEntryException extends RuntimeException {
	public DplicateEntryException(String email) {
		super("User already exists with this email:" + email);
		
	}

}
