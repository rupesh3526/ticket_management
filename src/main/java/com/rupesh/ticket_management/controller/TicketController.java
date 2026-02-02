package com.rupesh.ticket_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rupesh.ticket_management.entityDto.TicketDTO;
import com.rupesh.ticket_management.entityDto.response.TicketResponseDTO;
import com.rupesh.ticket_management.service.TicketService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ticket")
public class TicketController {
	@Autowired
	private TicketService ticketService;
	@GetMapping("ding")
	public String dong() {
		return "Dong";
	}
	
	@PostMapping("/createTicket")
	public ResponseEntity<String> createTicket(@Valid @RequestBody TicketDTO ticket){
 return ticketService.createTicket(ticket);
}
	
	@GetMapping("getTickets")
	public ResponseEntity<List<TicketResponseDTO>> getTickets(){
	return ticketService.getTickets();
	
}

}
