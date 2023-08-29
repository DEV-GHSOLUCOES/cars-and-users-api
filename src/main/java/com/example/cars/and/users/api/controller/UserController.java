package com.example.cars.and.users.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cars.and.users.api.dto.UserDTO;
import com.example.cars.and.users.api.model.User;
import com.example.cars.and.users.api.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/users")
@Api(value = "User Controller")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @ApiOperation("Get a list of all users")
    public ResponseEntity<List<UserDTO>> listUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    @ApiOperation("Create a new user")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        User userSave = userService.createUser(userDTO);
        UserDTO userResponseDTO = new UserDTO(userSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
    }
    
    @GetMapping("/{id}")
    @ApiOperation("Get a user by ID")
    public ResponseEntity<UserDTO> getUserById(@ApiParam("ID of the user") @PathVariable Long id) {
        User user = userService.getUserById(id);
        UserDTO userDTO = new UserDTO(user);
        return ResponseEntity.ok(userDTO);
    }
    
    @DeleteMapping("/{id}")
    @ApiOperation("Delete a user by ID")
    public ResponseEntity<?> deleteUserById(@ApiParam("ID of the user") @PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/{id}")
    @ApiOperation("Update a user by ID")
    public ResponseEntity<UserDTO> updateUserById(
            @ApiParam("ID of the user") @PathVariable Long id,
            @RequestBody @Valid UserDTO userDTO) {
        User userSave = userService.updateUserById(id, userDTO);
        UserDTO userResponseDTO = new UserDTO(userSave);
        return ResponseEntity.ok(userResponseDTO);
    }
}
