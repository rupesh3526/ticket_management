package com.rupesh.ticket_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rupesh.ticket_management.dto.CommentDTO;
import com.rupesh.ticket_management.dto.response.CommentResponseDTO;
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
	
	// DELETE comment
	@DeleteMapping("/deleteComment/{id}")
	public ResponseEntity<String> deleteComment(@PathVariable Long id) {
	    return cmntService.deleteComment(id);
	}

	// UPDATE comment
	@PutMapping("/updateComment/{id}")
	public ResponseEntity<String> updateComment(@PathVariable Long id,
	        @RequestParam String message) {
	    return cmntService.updateComment(id, message);
	}
}
