package com.rupesh.ticket_management.entityDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class TicketDTO {
	@NotBlank
	private String issue;
	@NotEmpty
	private String description;
	@NotBlank
	private String priority;
	@NotBlank
	private String status;
	@NotNull
	private Long createdBy;
	@NotNull
	private Long assignedTo;
	
}
