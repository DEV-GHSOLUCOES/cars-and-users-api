package com.example.cars.and.users.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cars.and.users.api.dto.UserDTO;
import com.example.cars.and.users.api.exceptions.EmailAlreadyExistsException;
import com.example.cars.and.users.api.exceptions.LoginAlreadyExistsException;
import com.example.cars.and.users.api.exceptions.UserNotFoundException;
import com.example.cars.and.users.api.model.Car;
import com.example.cars.and.users.api.model.User;
import com.example.cars.and.users.api.repository.CarRepository;
import com.example.cars.and.users.api.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CarRepository carRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

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
			// userDTO.setToken(user.getToken());

			userDTOs.add(userDTO);
		}

		return userDTOs;
	}

	public User createUser(User user) throws EmailAlreadyExistsException, LoginAlreadyExistsException {

		if (user != null) {

			if (user.getCars() != null) {
				if (!user.getCars().isEmpty()) {
					List<Car> list = new ArrayList<Car>();
					for (Car car : user.getCars()) {
						
						car.setUser(user);
						list.add(car);
					}
					
					user.setCars(list);
				}
				
			}

			if (userRepository.existsByEmail(user.getEmail())) {
				throw new EmailAlreadyExistsException();
			}

			if (userRepository.existsByLogin(user.getLogin())) {
				throw new LoginAlreadyExistsException();
			}

		}

		return this.userRepository.save(user);
	}

	public User getUserById(Long id) throws UserNotFoundException {

		Optional<User> user = userRepository.findById(id);

		if (user.isEmpty()) {

			throw new UserNotFoundException();

		}

		return user.get();
	}

	public void deleteById(Long id) throws UserNotFoundException {
		Optional<User> user = this.userRepository.findById(id);
		if (user.isPresent()) {

			User userOptional = user.get();
			List<Car> cars = userOptional.getCars();

			// Delete cars associated with the user
			carRepository.deleteAll(cars);

			// Delete the user
			userRepository.deleteById(id);
		} else {
			throw new UserNotFoundException();
		}

	}

	public User updateUserById(Long id, User user) throws EmailAlreadyExistsException, LoginAlreadyExistsException {

		User userSave = this.getUserById(id);

		// Verifica se o email já existe para outro usuário
		User existingUserWithEmail = userRepository.findFirstByEmail(user.getEmail());
		if (existingUserWithEmail != null && !existingUserWithEmail.getId().equals(id)) {
			throw new EmailAlreadyExistsException();
		}

		// Verifica se o login já existe par outro usuario
		User existingUserWithLogin = userRepository.findFirstByLogin(user.getLogin());
		if (existingUserWithLogin != null && !existingUserWithLogin.getId().equals(id)) {
			throw new LoginAlreadyExistsException();
		}

		BeanUtils.copyProperties(user, userSave, "id");

		return userRepository.save(userSave);
	}

}
