package com.rupesh.ticket_management.entityDto.response;

import java.util.List;
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
