package com.rupesh.ticket_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rupesh.ticket_management.entityDto.TicketDTO;
import com.rupesh.ticket_management.entityDto.response.TicketResponseDTO;
import com.rupesh.ticket_management.service.TicketService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

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
	public ResponseEntity<String> createTicket(@Valid @RequestBody TicketDTO ticket) {
		String msg = ticketService.createTicket(ticket);
		return ResponseEntity.status(HttpStatus.CREATED).body(msg);
	}

	@GetMapping("/getTickets")
	public ResponseEntity<Page<TicketResponseDTO>> getTickets(@RequestParam(defaultValue="0") int page,
			@RequestParam(defaultValue="5") int size,
			@RequestParam(defaultValue="asc") String direction,
			@RequestParam(defaultValue="createdBy") String sortBy) {
		Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() :Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(page, size, sort);
		Page<TicketResponseDTO>	ticketDTOPage =ticketService.getTickets(pageable);
		return  ResponseEntity.ok().body(ticketDTOPage);

	}

	@GetMapping("/getTicket/{id}")
	public ResponseEntity<TicketResponseDTO> getTicket(@NotNull @PathVariable Long id) {
		TicketResponseDTO ticketDTO =ticketService.getTicketById(id);
		return ResponseEntity.status(200).body(ticketDTO);

	}

}
