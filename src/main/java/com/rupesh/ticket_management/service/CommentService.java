package com.rupesh.ticket_management.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.rupesh.ticket_management.entityDto.CommentDTO;
import com.rupesh.ticket_management.entityDto.response.CommentResponseDTO;

public interface CommentService {
	
	ResponseEntity<String> createComment(CommentDTO cmntDTO);
	ResponseEntity<List<CommentResponseDTO>> findCmntByticketId(Long id);

}
