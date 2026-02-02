package com.rupesh.ticket_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rupesh.ticket_management.EntityDTO.TicketDTO;
import com.rupesh.ticket_management.EntityDTO.TicketResponseDTO;
import com.rupesh.ticket_management.EntityDTO.UserDTO;
import com.rupesh.ticket_management.EntityDTO.UserResponseDTO;
import com.rupesh.ticket_management.service.TicketService;
import com.rupesh.ticket_management.service.UserService;

import jakarta.validation.Valid;

@RestController
public class TmsController {
	@Autowired
	private UserService userService;
	@Autowired
	private TicketService ticketService;

	@GetMapping("ding")
	public String dong() {
		return "Dong";
	}

	@PostMapping("createUser")
	public void createUser(@Valid @RequestBody UserDTO userDto) {
		System.out.println(userDto.getEmail());

		userService.addUser(userDto);

	}

	@GetMapping("/getUser/{Id}")
	public ResponseEntity<UserResponseDTO> getUser(@Valid @PathVariable int Id) {
		return userService.getUser(Id);
	}

	@PostMapping("/createTicket")
	public ResponseEntity<String> createTicket(@Valid @RequestBody TicketDTO ticket){
 return ticketService.createTicket(ticket);
}
	
	@GetMapping("getTickets")
	public ResponseEntity<List<TicketResponseDTO>> getTickets(){
	return ticketService.getTickets();
	
}}