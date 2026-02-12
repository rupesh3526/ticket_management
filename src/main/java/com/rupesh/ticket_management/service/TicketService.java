package com.rupesh.ticket_management.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rupesh.ticket_management.entityDto.TicketDTO;
import com.rupesh.ticket_management.entityDto.response.TicketResponseDTO;

@Service
public interface TicketService{

	String createTicket(TicketDTO ticket);
	
	Page<TicketResponseDTO> getTickets(Pageable page);
	
	Page<TicketResponseDTO> getAllTickets(Pageable page);
	
	TicketResponseDTO getTicketById(Long id);
	
	
	
}
