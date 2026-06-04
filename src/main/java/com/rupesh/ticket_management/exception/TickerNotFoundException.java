package com.rupesh.ticket_management.exception;

public class TickerNotFoundException extends RuntimeException{
	
public TickerNotFoundException(Long id) {
	super("Ticket not found with id :"+ id);
}
}
