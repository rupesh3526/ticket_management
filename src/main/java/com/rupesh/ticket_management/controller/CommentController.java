package com.rupesh.ticket_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rupesh.ticket_management.entityDto.CommentDTO;
import com.rupesh.ticket_management.entityDto.response.CommentResponseDTO;
import com.rupesh.ticket_management.service.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private CommentService cmntService;
	
	@PostMapping("/createComment")
	public ResponseEntity<String> createComment(@Valid @RequestBody CommentDTO cmntDTO){
	return	cmntService.createComment(cmntDTO);
		}
	
	@GetMapping("/getCommentsByTicketId/{id}")
	public ResponseEntity<List<CommentResponseDTO>> getCmntByTcktId(@PathVariable Long id){
	return	cmntService.findCmntByticketId(id);
	}
}
