package com.rupesh.ticket_management.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
public class Comments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String message;
	@ManyToOne
	@JoinColumn(name = "Ticket_id", nullable = false)
	@JsonIgnore
	private Ticket ticket;
	@ManyToOne
	@JoinColumn(name = "User_id", nullable = false)
	@JsonIgnore
	private User user;

	private LocalDateTime created_at;

	@PrePersist
	public void onCreate() {
		created_at = LocalDateTime.now();

	}

}
