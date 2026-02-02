package com.rupesh.ticket_management.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rupesh.ticket_management.EntityDTO.TicketDTO;
import com.rupesh.ticket_management.EntityDTO.TicketResponseDTO;

@Service
public interface TicketService{

	ResponseEntity<String> createTicket(TicketDTO ticket);
	
	ResponseEntity<List<TicketResponseDTO>> getTickets();
	
}
