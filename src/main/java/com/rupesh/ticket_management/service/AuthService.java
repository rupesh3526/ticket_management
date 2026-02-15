package com.rupesh.ticket_management.service;

import com.rupesh.ticket_management.dto.AuthRequest;
import com.rupesh.ticket_management.dto.RefreshRequest;
import com.rupesh.ticket_management.dto.response.AuthResponse;

public interface AuthService {
 AuthResponse login(AuthRequest request);
 AuthResponse refreshJwtToken(RefreshRequest refreshToken);
}
