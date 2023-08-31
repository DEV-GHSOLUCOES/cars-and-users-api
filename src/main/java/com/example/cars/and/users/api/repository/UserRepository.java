package com.example.cars.and.users.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.cars.and.users.api.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public boolean existsByEmail(String email);

	public boolean existsByLogin(String login);

	public User findByEmail(String email);

	public User findByLogin(String login);
	
	public User findByPassword(String password);
	

	@Query("select u from User u where u.login = ?1")
	public User findByUserLogin(String login);

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "update user set token = ?1 where login = ?2")
	public void updateTokenUser(String token, String login);

}
