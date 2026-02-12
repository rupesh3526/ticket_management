package com.rupesh.ticket_management.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.rupesh.ticket_management.entity.Ticket;
import com.rupesh.ticket_management.entity.Users;
import com.rupesh.ticket_management.entityDto.TicketDTO;
import com.rupesh.ticket_management.entityDto.response.TicketResponseDTO;
import com.rupesh.ticket_management.exception.TickerNotFoundException;
import com.rupesh.ticket_management.exception.UserNotFoundException;
import com.rupesh.ticket_management.repository.TicketRepo;
import com.rupesh.ticket_management.repository.UserRepo;
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

	@Transactional
	public String createTicket(TicketDTO ticketDTO) {

		Ticket ticket = mapper.map(ticketDTO, Ticket.class);
		Users ticketCreater = userRepo.findById(ticketDTO.getCreatedBy()).orElseThrow();
		ticket.setCreatedBy(ticketCreater);
		Users assignedUser = userRepo.findById(ticketDTO.getAssignedTo()).orElseThrow();
		ticket.setAssignedTo(assignedUser);
		ticketRepo.save(ticket);

		return "Ticket successfully created";

	}

	@Override
	public Page<TicketResponseDTO> getTickets(Pageable page) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String email= auth.getName();
	Users currentUser=userRepo.findByEmail(email).orElseThrow(()->  new UserNotFoundException("Authenticated user no longer exists"));
	Page<Ticket> ticketPage	 = ticketRepo.findByCreatedBy(currentUser, page);
		
		Page<TicketResponseDTO> ticketDTOPage = ticketPage.map(ticket -> {
			TicketResponseDTO dto = mapper.map(ticket, TicketResponseDTO.class);
			dto.setAssignedTo(ticket.getAssignedTo().getName());
			dto.setCreatedBy(ticket.getCreatedBy().getName());
			return dto;
		});
		return ticketDTOPage;
	}

	@Override
	public TicketResponseDTO getTicketById(Long id) {
		Ticket ticket = ticketRepo.findById(id).orElseThrow(() -> new TickerNotFoundException(id));
		TicketResponseDTO ticketDTO = mapper.map(ticket, TicketResponseDTO.class);
		ticketDTO.setAssignedTo(ticket.getAssignedTo().getName());
		ticketDTO.setCreatedBy(ticket.getCreatedBy().getName());

		return ticketDTO;
	}

	@Override
	public Page<TicketResponseDTO> getAllTickets(Pageable page) {
		Page<Ticket> ticketPage = ticketRepo.findAll(page);
		Page<TicketResponseDTO> pageDTO = ticketPage.map(ticket -> {
			TicketResponseDTO dtoTicket = mapper.map(ticket, TicketResponseDTO.class);
			if (ticket.getAssignedTo() != null) {
	            dtoTicket.setAssignedTo(ticket.getAssignedTo().getName());
	        }
			dtoTicket.setCreatedBy(ticket.getCreatedBy().getName());
			return dtoTicket;
		});
		return pageDTO;
	}

}
