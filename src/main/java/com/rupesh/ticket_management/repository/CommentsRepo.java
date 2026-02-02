package com.rupesh.ticket_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rupesh.ticket_management.entity.Comments;

public interface CommentsRepo extends JpaRepository<Comments, Integer> {
	

}
