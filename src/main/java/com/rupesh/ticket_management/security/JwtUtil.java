package com.rupesh.ticket_management.security;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	private final static String secretKey = "THIS_IS_A_VERY_LONG_256_BIT_SECRET_KEY_FOR_JWT_SIGNING_123456";
	private static Logger logger = LoggerFactory.getLogger(JwtUtil.class);

	// generate Tokens
	public String generateToken(UserDetails user) {
		Date date = new Date();
		Date expiry = new Date(System.currentTimeMillis() + 1000 * 60 * 60);
		String token = Jwts.builder().setSubject(user.getUsername()).setIssuedAt(date)
				.claim("roles", user.getAuthorities()).setExpiration(expiry)
				.signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256).compact();
		logger.debug("Generating JWT for username={} with expiry={}", user.getUsername(), expiry);
		return token;
	}

	public String generateRefreshToken(UserDetails user) {
		Date date = new Date();
		Date expiry = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 15);

		String refreshToken = Jwts.builder().setSubject(user.getUsername()).setIssuedAt(date).setExpiration(expiry)
				.signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256).compact();

		logger.debug("Generating JWT for username={} with expiry={}", user.getUsername(), expiry);

		return refreshToken;
	}

	// extraction of username and claims
	public String extractUsername(String token) {
		return getClaims(token).getSubject();
	}
	
	

	public boolean isTokenValid(String token) {
		try {
			Claims claims = getClaims(token);
			return !claims.getExpiration().before(new Date());
		} catch (Exception e) {
			return false;
		}
	}

	private Claims getClaims(String token) {

		Claims claim = Jwts.parserBuilder().setSigningKey(secretKey.getBytes()).build().parseClaimsJws(token).getBody();
		return claim;

	}

}
