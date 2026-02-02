package com.rupesh.ticket_management.serviceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rupesh.ticket_management.Entity.Ticket;
import com.rupesh.ticket_management.Entity.User;
import com.rupesh.ticket_management.EntityDTO.TicketDTO;
import com.rupesh.ticket_management.EntityDTO.TicketResponseDTO;
import com.rupesh.ticket_management.Jpa.TicketRepo;
import com.rupesh.ticket_management.Jpa.UserRepo;
import com.rupesh.ticket_management.security.CreaterProvider;
import com.rupesh.ticket_management.service.TicketService;

import jakarta.transaction.Transactional;

@Service
public class TicketServiceImp implements TicketService {
	@Autowired
	private TicketRepo ticketRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private CreaterProvider createrProvider;

	@Transactional
	public ResponseEntity<String> createTicket(TicketDTO ticketDTO) {

		Ticket ticket = mapper.map(ticketDTO, Ticket.class);

		// Set who created the ticket by using a auth createdProvider method so future
		// implementation of security can be easy
		User ticketCreater = createrProvider.getCreater(userRepo, ticketDTO.getCreated_by());
		ticket.setCreated_by(ticketCreater);
		// Set ticket assigned to
		User assignedUser = userRepo.findById(ticketDTO.getAssigned_to()).orElseThrow();
		ticket.setAssigned_to(assignedUser);
		// ticket.setComments(null);

		ticketRepo.save(ticket);

		return ResponseEntity.status(HttpStatus.CREATED).body("Ticket successfully created");

	}

	@Override
	public ResponseEntity<List<TicketResponseDTO>> getTickets() {

		List<Ticket> ticketList = ticketRepo.findAll();
		List<TicketResponseDTO> ticketDTOList = ticketList.stream()
				.map(ticket -> {
					TicketResponseDTO dto = mapper.map(ticket, TicketResponseDTO.class);
					dto.setAssigned_to(ticket.getAssigned_to().getName());
					dto.setCreated_by(ticket.getCreated_by().getName());
					return dto;
								})
					.collect(Collectors.toList());
		return ResponseEntity.ok().body(ticketDTOList);
	}

}
