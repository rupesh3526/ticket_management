package com.rupesh.ticket_management.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rupesh.ticket_management.entity.Ticket;
import com.rupesh.ticket_management.entity.User;
import com.rupesh.ticket_management.entityDto.TicketDTO;
import com.rupesh.ticket_management.entityDto.response.TicketResponseDTO;
import com.rupesh.ticket_management.repository.TicketRepo;
import com.rupesh.ticket_management.repository.UserRepo;
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
		User ticketCreater = createrProvider.getCreater(userRepo, ticketDTO.getCreatedBy());
		ticket.setCreatedBy(ticketCreater);
		// Set ticket assigned to
		User assignedUser = userRepo.findById(ticketDTO.getAssignedTo()).orElseThrow();
		ticket.setAssignedTo(assignedUser);
		// ticket.setComments(null);

		ticketRepo.save(ticket);

		return ResponseEntity.status(HttpStatus.CREATED).body("Ticket successfully created");

	}

	@Override
	public ResponseEntity<List<TicketResponseDTO>> getTickets() {

		List<Ticket> ticketList = ticketRepo.findAll();
		List<TicketResponseDTO> ticketDTOList = ticketList.stream().map(ticket -> {
			TicketResponseDTO dto = mapper.map(ticket, TicketResponseDTO.class);
			dto.setAssignedTo(ticket.getAssignedTo().getName());
			dto.setCreatedBy(ticket.getCreatedBy().getName());
			return dto;
		}).collect(Collectors.toList());
		return ResponseEntity.ok().body(ticketDTOList);
	}

	@Override
	public ResponseEntity<TicketResponseDTO> findTicketByIdWithComments() {
		// TODO Auto-generated method stub
		return null;
	}

}
