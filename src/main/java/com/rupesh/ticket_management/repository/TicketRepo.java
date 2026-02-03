package com.rupesh.ticket_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rupesh.ticket_management.entity.Ticket;

public interface TicketRepo extends JpaRepository<Ticket, Long> {

}
