package com.rupesh.ticket_management.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String password;
	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	@JsonIgnore
	private Role role;
	@OneToMany(mappedBy = "created_by")
	private List<Ticket> createdTicket;
	@OneToMany(mappedBy = "assigned_to")
	private List<Ticket> assignedTicket;

}
