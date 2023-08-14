package com.example.cars.and.users.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.example.cars.and.users.api.dto.UserDTO;
import com.example.cars.and.users.api.exceptions.EmailAlreadyExistsException;
import com.example.cars.and.users.api.exceptions.LoginAlreadyExistsException;
import com.example.cars.and.users.api.model.User;
import com.example.cars.and.users.api.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	
	@Autowired
	 private MessageSource messageSource;
	
	 public List<UserDTO> getAllUsers() {
	        List<User> users = userRepository.findAll();
	        List<UserDTO> userDTOs = new ArrayList<>();

	        for (User user : users) {
	            UserDTO userDTO = new UserDTO();
	            
	            userDTO.setId(Long.toString(user.getId()));
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


	public User createUser(UserDTO userDTO) throws EmailAlreadyExistsException, LoginAlreadyExistsException  {
		User userModel = new User();
		userModel.setFirstName(userDTO.getFirstName());
		userModel.setLastName(userDTO.getLastName());
		userModel.setBirthday((userDTO.getBirthday()));
		userModel.setPassword(userDTO.getPassword());
		userModel.setPhone(userDTO.getPhone());
		userModel.setCars(userDTO.getCars());
		
		// Verifica se o email já existe
		userModel.setEmail(userDTO.getEmail());
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new EmailAlreadyExistsException();
        }
        
        // Verifica se o login já existe
        userModel.setLogin(userDTO.getLogin());
        if (userRepository.existsByLogin(userDTO.getLogin())) {
            throw new LoginAlreadyExistsException();
        }
		
		
		
		 return this.userRepository.save(userModel);
	}

	

}
