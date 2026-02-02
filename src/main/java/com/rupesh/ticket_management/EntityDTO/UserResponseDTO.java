package com.rupesh.ticket_management.EntityDTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rupesh.ticket_management.EntityDTO.RoleRequestDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDTO {
	
	private int id;
	private String name;
	private String email;
	
	private String  role;
	
	private List<TicketResponseDTO> createdTicket;
	private List<TicketResponseDTO> assignedTicket;

}
