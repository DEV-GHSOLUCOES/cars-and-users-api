package com.example.cars.and.users.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cars.and.users.api.dto.CarDTO;
import com.example.cars.and.users.api.dto.UserDTO;
import com.example.cars.and.users.api.model.Car;
import com.example.cars.and.users.api.model.User;
import com.example.cars.and.users.api.service.CarService;

import io.jsonwebtoken.security.SignatureException;

@RestController
@RequestMapping("api/cars")
public class CarController {

	@Autowired
	private CarService carService;

	@GetMapping
	public ResponseEntity<List<CarDTO>> listCars() throws AccessDeniedException, AuthenticationException {
		List<CarDTO> listCars = carService.getAllCars();
		return ResponseEntity.ok(listCars);
	}

	@PostMapping
	public ResponseEntity<CarDTO> createCar(@Valid @RequestBody CarDTO carDTO) throws SignatureException {
		Car carSave = carService.createCar(carDTO);
		CarDTO carResponseDTO = new CarDTO(carSave);
		return ResponseEntity.status(HttpStatus.CREATED).body(carResponseDTO);

	}

	
	@GetMapping("/{id}")
	public ResponseEntity<CarDTO> getCarById(@PathVariable Long id) {

		Car car = carService.getCarById(id);
		CarDTO carDTO = new CarDTO(car);

		return ResponseEntity.ok(carDTO);

	}

	/*
	 * @DeleteMapping("/{id}") public void deleteUserById(@PathVariable Long id) {
	 * userService.deleteById(id); }
	 * 
	 * @PutMapping("/{id}") public ResponseEntity<UserDTO>
	 * updateUserById(@PathVariable Long id, @RequestBody @Valid UserDTO userDTO) {
	 * User userSave = userService.updateUserById(id, userDTO);
	 * 
	 * UserDTO userResponseDTO = new UserDTO(userSave);
	 * 
	 * return ResponseEntity.ok(userResponseDTO); }
	 */

}
