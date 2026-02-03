package com.rupesh.ticket_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rupesh.ticket_management.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Long> {
	List<Comment> findByTicketId(Long ticketId);

}
