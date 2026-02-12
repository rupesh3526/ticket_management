package com.rupesh.ticket_management.exception;

public class DuplicateEntryException extends RuntimeException {
	public DuplicateEntryException(String email) {
		super("User already exists with this email:" + email);
		
	}

}
