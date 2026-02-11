package com.rupesh.ticket_management.entityDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshRequest {

	private String Token;
	@NotBlank(message = "Refresh token is required")
	private String refreshToken;

}
