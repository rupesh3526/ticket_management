package com.rupesh.ticket_management.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rupesh.ticket_management.dto.TicketDTO;
import com.rupesh.ticket_management.dto.response.TicketResponseDTO;
import com.rupesh.ticket_management.entity.Ticket;
import com.rupesh.ticket_management.entity.Users;
import com.rupesh.ticket_management.exception.TickerNotFoundException;
import com.rupesh.ticket_management.exception.UserNotFoundException;
import com.rupesh.ticket_management.repository.TicketRepo;
import com.rupesh.ticket_management.repository.UserRepo;
import com.rupesh.ticket_management.service.TicketService;

@Service
public class TicketServiceImp implements TicketService {
	@Autowired
	private TicketRepo ticketRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper mapper;
	@Value("${gemini.api.key}")
	private String geminiApiKey;
	@Autowired
	private RestTemplate restTemplate;	

	@Transactional
	public String createTicket(TicketDTO ticketDTO) {

		Ticket ticket = mapper.map(ticketDTO, Ticket.class);
		Users ticketCreater = userRepo.findById(ticketDTO.getCreatedBy()).orElseThrow();
		ticket.setCreatedBy(ticketCreater);
		Users assignedUser = userRepo.findById(ticketDTO.getAssignedTo()).orElseThrow();
		ticket.setAssignedTo(assignedUser);
		 String summary = generateAiSummary(ticketDTO);
		    ticket.setAiSummary(summary);
		ticketRepo.save(ticket);

		return "Ticket successfully created";

	}
	private String generateAiSummary(TicketDTO ticketDTO) {
	    try {
	        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash-lite:generateContent?key=" + geminiApiKey;

	        String prompt = """
	            Analyze this support ticket and give a 2-3 line summary:
	            
	            Issue: %s
	            Description: %s
	            Priority: %s
	            Status: %s
	            """.formatted(
	                ticketDTO.getIssue(),
	                ticketDTO.getDescription(),
	                ticketDTO.getPriority(),
	                ticketDTO.getStatus()
	            );

	        String requestBody = """
	            {
	              "contents": [{
	                "parts": [{"text": "%s"}]
	              }]
	            }
	            """.formatted(prompt.replace("\"", "\\\"").replace("\n", "\\n"));
	        
	        Map<String, Object> part = Map.of("text", prompt);
	        Map<String, Object> content = Map.of("parts", List.of(part));
	        Map<String, Object> requestBody2 = Map.of("contents", List.of(content));

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);

	     // Create request entity
	        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody2, headers);

	        // Make POST call
	        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
			/*
			 * HttpClient client = HttpClient.newHttpClient(); HttpRequest request =
			 * HttpRequest.newBuilder() .uri(URI.create(url)) .header("Content-Type",
			 * "application/json") .POST(HttpRequest.BodyPublishers.ofString(requestBody))
			 * .build();
			 */
	   //     HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

	       

	        ObjectMapper objectMapper = new ObjectMapper();
	        JsonNode root = objectMapper.readTree(response.getBody());
	        JsonNode candidates = root.path("candidates");
	        if (candidates.isEmpty()) return "Summary not available";

	        return candidates.get(0)
	                .path("content")
	                .path("parts")
	                .get(0)
	                .path("text")
	                .asText("Summary not available");

	    } catch (Exception e) {
	        System.err.println("Gemini Error: " + e.getMessage());
	        return "Summary not available";
	    }
	}
	@Override
	@Transactional(readOnly = true)
	public Page<TicketResponseDTO> getTickets(Pageable page) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		Users currentUser = userRepo.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException("Authenticated user no longer exists"));
		Page<Ticket> ticketPage = ticketRepo.findByCreatedBy(currentUser, page);

		Page<TicketResponseDTO> ticketDTOPage = ticketPage.map(ticket -> {
			TicketResponseDTO dto = mapper.map(ticket, TicketResponseDTO.class);
			dto.setAssignedTo(ticket.getAssignedTo().getName());
			dto.setCreatedBy(ticket.getCreatedBy().getName());
			return dto;
		});
		return ticketDTOPage;
	}

	@Override
	@Transactional(readOnly = true)
	public TicketResponseDTO getTicketById(Long id) {
		Ticket ticket = ticketRepo.findById(id).orElseThrow(() -> new TickerNotFoundException(id));
		TicketResponseDTO ticketDTO = mapper.map(ticket, TicketResponseDTO.class);
		if (ticket.getAssignedTo() != null)
			ticketDTO.setAssignedTo(ticket.getAssignedTo().getName());
		ticketDTO.setCreatedBy(ticket.getCreatedBy().getName());
		ticketDTO.setAiSummary(ticket.getAiSummary());
		return ticketDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<TicketResponseDTO> getAllTickets(Pageable page) {
		Page<Ticket> ticketPage = ticketRepo.findAll(page);
		Page<TicketResponseDTO> pageDTO = ticketPage.map(ticket -> {
			TicketResponseDTO dtoTicket = mapper.map(ticket, TicketResponseDTO.class);
			if (ticket.getAssignedTo() != null) {
				dtoTicket.setAssignedTo(ticket.getAssignedTo().getName());
			}
			dtoTicket.setCreatedBy(ticket.getCreatedBy().getName());
			return dtoTicket;
		});
		return pageDTO;
	}
	@Override
	@Transactional
	public String updateStatus(Long id, String status) {
	    Ticket ticket = ticketRepo.findById(id)
	            .orElseThrow(() -> new TickerNotFoundException(id));
	    ticket.setStatus(status);
	    ticketRepo.save(ticket);
	    return "Ticket status updated to: " + status;
	}

	@Override
	@Transactional
	public String deleteTicket(Long id) {
	    Ticket ticket = ticketRepo.findById(id)
	            .orElseThrow(() -> new TickerNotFoundException(id));
	    ticketRepo.delete(ticket);
	    return "Ticket deleted successfully";
	}

	@Override
	@Transactional
	public String updateTicket(Long id, TicketDTO ticketDTO) {
	    Ticket ticket = ticketRepo.findById(id)
	            .orElseThrow(() -> new TickerNotFoundException(id));
	    ticket.setIssue(ticketDTO.getIssue());
	    ticket.setDescription(ticketDTO.getDescription());
	    ticket.setPriority(ticketDTO.getPriority());
	    ticket.setStatus(ticketDTO.getStatus());
	    ticketRepo.save(ticket);
	    return "Ticket updated successfully";
	}

	@Override
	@Transactional(readOnly = true)
	public List<TicketResponseDTO> getTicketsByStatus(String status) {
	    List<Ticket> tickets = ticketRepo.findByStatus(status);
	    return tickets.stream().map(ticket -> {
	        TicketResponseDTO dto = mapper.map(ticket, TicketResponseDTO.class);
	        dto.setCreatedBy(ticket.getCreatedBy().getName());
	        dto.setAssignedTo(ticket.getAssignedTo().getName());
	        return dto;
	    }).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public List<TicketResponseDTO> getTicketsByPriority(String priority) {
	    List<Ticket> tickets = ticketRepo.findByPriority(priority);
	    return tickets.stream().map(ticket -> {
	        TicketResponseDTO dto = mapper.map(ticket, TicketResponseDTO.class);
	        dto.setCreatedBy(ticket.getCreatedBy().getName());
	        dto.setAssignedTo(ticket.getAssignedTo().getName());
	        return dto;
	    }).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public String regenerateSummary(Long id) {
	    Ticket ticket = ticketRepo.findById(id)
	            .orElseThrow(() -> new TickerNotFoundException(id));
	    TicketDTO dto = new TicketDTO();
	    dto.setIssue(ticket.getIssue());
	    dto.setDescription(ticket.getDescription());
	    dto.setPriority(ticket.getPriority());
	    dto.setStatus(ticket.getStatus());
	    String summary = generateAiSummary(dto);
	    ticket.setAiSummary(summary);
	    ticketRepo.save(ticket);
	    return "AI Summary regenerated successfully";
	}

}
