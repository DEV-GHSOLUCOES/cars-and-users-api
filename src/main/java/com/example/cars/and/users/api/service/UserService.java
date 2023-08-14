package com.example.cars.and.users.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cars.and.users.api.dto.UserDTO;
import com.example.cars.and.users.api.model.User;
import com.example.cars.and.users.api.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	
	 public List<UserDTO> getAllUsers() {
	        List<User> users = userRepository.findAll();
	        List<UserDTO> userDTOs = new ArrayList<>();

	        for (User user : users) {
	            UserDTO userDTO = new UserDTO();
	            
	            
	            userDTO.setId(user.getId());
	            userDTO.setFirstName(user.getFirstName());
	            userDTO.setLastName(user.getLastName());
	            userDTO.setEmail(user.getEmail());
	            userDTO.setBirthday(user.getBirthday());
	            userDTO.setCars(user.getCars());
	            userDTO.setLogin(user.getLogin());
	            userDTO.setPhone(user.getPhone());
	            
	            
	            userDTOs.add(userDTO);
	        }

	        return userDTOs;
	    }

	

}
