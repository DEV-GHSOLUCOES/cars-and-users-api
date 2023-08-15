package com.example.cars.and.users.api.controller;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cars.and.users.api.dto.UserDTO;
import com.example.cars.and.users.api.model.User;
import com.example.cars.and.users.api.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> listUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
		User usuarioSalvo = userService.createUser(userDTO);
		UserDTO userResponseDTO = new UserDTO(usuarioSalvo);
		return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);

	}
    
    
    @GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById (@PathVariable Long id) {

		User user = userService.getUserById(id);
		UserDTO userDTO = new UserDTO(user);
		
		return  ResponseEntity.ok(userDTO);
		
		//return null;

	}
}
