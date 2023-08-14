package com.example.cars.and.users.api.exceptions;

public class EmailAlreadyExistsException  extends RuntimeException{
	
	public EmailAlreadyExistsException(String message) {
        super(message);
    }

	public EmailAlreadyExistsException() {
		
	}

}
