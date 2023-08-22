package com.example.cars.and.users.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CarAndUserApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarAndUserApiApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("password123"));
	}

}
