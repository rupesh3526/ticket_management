package com.rupesh.ticket_management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rupesh.ticket_management.entity.UserSession;
@Repository
public interface LoginSessionRepo extends JpaRepository<UserSession,Long> {
Optional<UserSession> findByTokenHash(String hashToken);
}
