package com.rupesh.ticket_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort;
import com.rupesh.ticket_management.entityDto.response.TicketResponseDTO;
import com.rupesh.ticket_management.entityDto.response.UserResponseDTO;
import com.rupesh.ticket_management.service.TicketService;
import com.rupesh.ticket_management.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private TicketService ticketService;
	@Autowired
	private UserService userService;

	@GetMapping("/testAdmin")
	public boolean admin() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean isAdmin = auth.getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("ROLE_ADMIN"));
		return isAdmin;
	}

	@GetMapping("/tickets")
	public ResponseEntity<Page<TicketResponseDTO>> getTickets(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "createdBy") String sortBy,
			@RequestParam(defaultValue = "desc") String direction) {
		Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(page, size, sort);
		Page<TicketResponseDTO> ticketDTOPage = ticketService.getAllTickets(pageable);
		return ResponseEntity.ok(ticketDTOPage);
	}
	
	@GetMapping("updateRole")
	public ResponseEntity<?> updateRole(@RequestParam(required = true ) Long userId , @RequestParam Integer roleId){
	UserResponseDTO userDTO=	userService.updateRole(userId, roleId);
	return ResponseEntity.ok(userDTO);
	}

}