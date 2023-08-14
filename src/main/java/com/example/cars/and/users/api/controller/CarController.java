package com.example.cars.and.users.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cars.and.users.api.dto.CarDTO;
import com.example.cars.and.users.api.service.CarService;

@RestController
@RequestMapping("api/cars")
public class CarController {
	
	
	@Autowired
	private CarService carService;
	
	 @GetMapping
	    public ResponseEntity<List<CarDTO>> listCars() {
	        List<CarDTO> listCars = carService.getAllCars();
	        return ResponseEntity.ok(listCars);
	    }
	 
	 

}
