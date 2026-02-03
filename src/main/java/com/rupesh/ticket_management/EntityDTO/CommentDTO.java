package com.rupesh.ticket_management.entityDto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {
	@NotEmpty
	private String message;
	@NotNull
	private Long ticketId;
	@NotNull
	private Long userId;
	}
