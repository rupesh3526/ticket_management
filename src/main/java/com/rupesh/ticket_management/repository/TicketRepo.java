package com.rupesh.ticket_management.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rupesh.ticket_management.entity.Ticket;
import com.rupesh.ticket_management.entity.Users;

public interface TicketRepo extends JpaRepository<Ticket, Long> {
	Page<Ticket> findByCreatedBy(Users user, Pageable pageable);

}
