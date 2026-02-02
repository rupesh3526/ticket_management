package com.rupesh.ticket_management.entityDto;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class TicketDTO {
	
	private String issue;
	private String description;
	private String priority;
	private String status;
	private Integer created_by;
	private Integer assigned_to;
	
}
