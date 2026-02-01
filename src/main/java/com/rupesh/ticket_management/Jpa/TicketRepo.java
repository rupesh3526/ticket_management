package com.rupesh.ticket_management.Jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rupesh.ticket_management.Entity.Ticket;

public interface TicketRepo extends JpaRepository<Ticket, Integer> {

}
