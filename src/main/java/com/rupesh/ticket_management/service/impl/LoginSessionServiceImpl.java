package com.rupesh.ticket_management.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rupesh.ticket_management.entity.UserSession;
import com.rupesh.ticket_management.entity.Users;
import com.rupesh.ticket_management.exception.UnauthorizedException;
import com.rupesh.ticket_management.repository.LoginSessionRepo;
import com.rupesh.ticket_management.security.JwtUtil;
import com.rupesh.ticket_management.service.LoginSessionService;


@Service
public class LoginSessionServiceImpl implements LoginSessionService {
	@Autowired
	private LoginSessionRepo loginSessionRepo;
	@Autowired
	private JwtUtil jwtUtil;
	private static final Logger logger = LoggerFactory.getLogger(LoginSessionServiceImpl.class);

	@Override
	public void saveSession(Users user, String token) {

		UserSession userSession = new UserSession();
	String hashToken=	hashToken(token);
	logger.info("login token :{}",hashToken);
		userSession.setTokenHash(hashToken);
		logger.info("login token :{}",hashToken(token));
		userSession.setUser(user);
		userSession.setRevoked(false);
		userSession.setToken_version_snap(user.getToken_version());
		userSession.setExpiry_time(LocalDateTime.now().plusHours(1));
		userSession.setLastUsedAt(LocalDateTime.now());

		loginSessionRepo.save(userSession);

	}

	@Override
	public void revokeSession() {

	}

	@Override
	public void revokeAllSession() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteSession() {
		// TODO Auto-generated method stub

	}

	public UserSession validateSession(String token) {
		String hashToken = hashToken(token);
		logger.info("token :{}", hashToken);
	    UserSession userSession = loginSessionRepo.findByTokenHash(hashToken)
	            .orElseThrow(() -> new UnauthorizedException("Invalid refresh token"));

		
	    if (userSession.isRevoked()) {
	        throw new UnauthorizedException("Refresh token already revoked");
	    }
		
	    if (!jwtUtil.isTokenValid(token)) {
	        throw new UnauthorizedException("Refresh token expired");
	    }
	    
	    return userSession;
	}

	private String hashToken(String token) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(token.getBytes(StandardCharsets.UTF_8));
			return Base64.getEncoder().encodeToString(hash);
		} catch (Exception e) {
			throw new RuntimeException("Could not hash token", e);
		}
	}

}
