package com.rupesh.ticket_management.entityDto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDTO {
	private String message;
	
	private String userName;
	private String ticketIssue;

}
