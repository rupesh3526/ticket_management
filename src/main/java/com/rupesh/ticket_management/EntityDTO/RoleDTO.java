package com.rupesh.ticket_management.entityDto;

import java.util.List;

import com.rupesh.ticket_management.entityDto.response.UserResponseDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class RoleDTO {
	
	private int id;
	private String name;
	
	private List<UserResponseDTO> userlist;

}
