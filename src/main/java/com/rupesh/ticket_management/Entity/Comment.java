package com.rupesh.ticket_management.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 1000,nullable = false)
	private String message;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CommentedOn", nullable = false)
	@JsonIgnore
	private Ticket ticket;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CommentedBy", nullable = false)
	private Users user;

	private LocalDateTime created_at;

	@PrePersist
	public void onCreate() {
		created_at = LocalDateTime.now();

	}

}
