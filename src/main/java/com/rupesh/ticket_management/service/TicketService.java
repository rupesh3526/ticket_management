package com.rupesh.ticket_management.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rupesh.ticket_management.entityDto.TicketDTO;
import com.rupesh.ticket_management.entityDto.response.TicketResponseDTO;

@Service
public interface TicketService{

	String createTicket(TicketDTO ticket);
	
	List<TicketResponseDTO> getTickets();
	
	TicketResponseDTO getTicketById(Long id);
	
}
