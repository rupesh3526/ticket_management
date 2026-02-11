package com.rupesh.ticket_management.entityDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
	@NotBlank
	private String name;
	@Email
	private String email;
	@Size(min = 8, message = "Password must be at least 8 characters long")
	private String password;
	private Integer roleId = 1;

}
