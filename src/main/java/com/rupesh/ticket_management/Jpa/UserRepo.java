package com.rupesh.ticket_management.Jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rupesh.ticket_management.Entity.User;
@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
	

}
