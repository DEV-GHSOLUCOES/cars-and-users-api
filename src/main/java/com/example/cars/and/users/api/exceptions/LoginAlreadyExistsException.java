package com.example.cars.and.users.api.exceptions;

public class LoginAlreadyExistsException   extends RuntimeException{

	public LoginAlreadyExistsException(String message) {
        super(message);
    }

	public LoginAlreadyExistsException() {
		
	}
}
