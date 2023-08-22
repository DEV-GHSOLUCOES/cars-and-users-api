package com.example.cars.and.users.api.dto;

public class JwtResponseDTO {
	//private User user;
	private String token;

	public JwtResponseDTO(/* User user2, */String jwt) {
		
	}

	/*
	 * public User getUser() { return user; }
	 * 
	 * public void setUser(User user) { this.user = user; }
	 */

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
