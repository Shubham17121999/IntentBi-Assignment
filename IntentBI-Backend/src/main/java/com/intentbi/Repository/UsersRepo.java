package com.intentbi.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intentbi.Entity.Users;

public interface UsersRepo extends JpaRepository<Users, Integer> {
	
	public Optional<Users> findByUsername(String username);
}
