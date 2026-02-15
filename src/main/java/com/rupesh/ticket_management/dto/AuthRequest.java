package com.rupesh.ticket_management.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
	@NotEmpty
	String username;
	@NotEmpty
	String password;

}
