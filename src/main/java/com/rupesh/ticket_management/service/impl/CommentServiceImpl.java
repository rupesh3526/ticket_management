package com.rupesh.ticket_management.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rupesh.ticket_management.entity.Comment;
import com.rupesh.ticket_management.entityDto.CommentDTO;
import com.rupesh.ticket_management.entityDto.response.CommentResponseDTO;
import com.rupesh.ticket_management.repository.CommentRepo;
import com.rupesh.ticket_management.repository.TicketRepo;
import com.rupesh.ticket_management.repository.UserRepo;
import com.rupesh.ticket_management.security.CreaterProvider;
import com.rupesh.ticket_management.service.CommentService;

import jakarta.transaction.Transactional;
@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepo cmntRepo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private CreaterProvider createrProvider;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private TicketRepo ticketRepo;
	

	@Override
	@Transactional
	public ResponseEntity<String> createComment(CommentDTO cmntDTO) {
		// Comment cmnt =mapper.map(cmntDTO, Comment.class);
		Comment cmnt = new Comment();
		cmnt.setMessage(cmntDTO.getMessage());
		cmnt.setTicket(ticketRepo.findById(cmntDTO.getTicketId()).orElseThrow());
		cmnt.setUser(createrProvider.getCreater(userRepo, cmntDTO.getUserId()));
		cmntRepo.save(cmnt);
		return ResponseEntity.status(HttpStatus.CREATED).body("Commented Successfully");
	}

	@Override
	public ResponseEntity<List<CommentResponseDTO>> findCmntByticketId(Long id) {
	List<Comment> cmntList=	cmntRepo.findByTicketId(id);
	List<CommentResponseDTO> cmntDTO =
			cmntList.stream()
			.map(cmnt ->{CommentResponseDTO dto = mapper.map(cmnt,CommentResponseDTO.class);
				 dto.setTicketIssue(cmnt.getTicket().getIssue());
				return dto;
			}
					)
			.collect(Collectors.toList());
		
		return ResponseEntity.ok(cmntDTO);
	}

}
