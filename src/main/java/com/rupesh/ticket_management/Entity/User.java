package com.rupesh.ticket_management.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
	@OneToMany(mappedBy = "createdBy")
	@JsonIgnore
	private List<Ticket> createdTicket;
	@OneToMany(mappedBy = "assignedTo")
	@JsonIgnore
	private List<Ticket> assignedTicket;

}
