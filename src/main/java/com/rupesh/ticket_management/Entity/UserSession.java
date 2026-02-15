package com.rupesh.ticket_management.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserSession {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "user", nullable = false)
	private Users user;
	@Column(nullable = false,length =255)
	private String tokenHash;
	@Column(nullable = false)
	private LocalDateTime expiry_time;
	@Column(nullable = false)
	private Long token_version_snap;
	@Column(nullable = false)
	private boolean revoked;
	@Column(nullable = false)
	private LocalDateTime createdAt;
	private LocalDateTime lastUsedAt;

	
	@PrePersist
	void onCreate() {
		this.createdAt=LocalDateTime.now();
	}
	

}
