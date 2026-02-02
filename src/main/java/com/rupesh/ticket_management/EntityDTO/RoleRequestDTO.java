package com.rupesh.ticket_management.EntityDTO;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class RoleRequestDTO {
	
	private int id;
	private String name;
	
	private List<UserResponseDTO> userlist;

}
