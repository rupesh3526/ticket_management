package com.rupesh.ticket_management.Jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rupesh.ticket_management.Entity.Comments;

public interface CommentsRepo extends JpaRepository<Comments, Integer> {
	

}
