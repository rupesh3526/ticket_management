package com.rupesh.ticket_management.entityDto.response;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketResponseDTO {
	
	private String issue;
	private String description;
	private String priority;
	private String status;
	private String createdBy;
	private String assignedTo;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
