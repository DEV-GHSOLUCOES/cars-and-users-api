package com.example.cars.and.users.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cars.and.users.api.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public boolean existsByEmail(String email);

	public boolean existsByLogin(String login);
    
}
