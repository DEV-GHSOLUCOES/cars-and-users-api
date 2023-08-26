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
import com.example.cars.and.users.api.model.Car;
import com.example.cars.and.users.api.service.CarService;

import io.jsonwebtoken.security.SignatureException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("api/cars")
@Api(value = "Car Controller")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    @ApiOperation("Get a list of all cars")
    public ResponseEntity<List<CarDTO>> listCars() throws AccessDeniedException, AuthenticationException {
        List<CarDTO> listCars = carService.getAllCars();
        return ResponseEntity.ok(listCars);
    }

    @PostMapping
    @ApiOperation("Create a new car")
    public ResponseEntity<CarDTO> createCar(@Valid @RequestBody CarDTO carDTO) throws SignatureException {
        Car carSave = carService.createCar(carDTO);
        CarDTO carResponseDTO = new CarDTO(carSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(carResponseDTO);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a car by ID")
    public ResponseEntity<CarDTO> getCarById(@ApiParam("ID of the car") @PathVariable Long id) {
        Car car = carService.getCarById(id);
        CarDTO carDTO = new CarDTO(car);
        return ResponseEntity.ok(carDTO);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete a car by ID")
    public ResponseEntity<Void> deleteCarById(@ApiParam("ID of the car") @PathVariable Long id) {
        carService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ApiOperation("Update a car by ID")
    public ResponseEntity<CarDTO> updateCarById(
            @ApiParam("ID of the car") @PathVariable Long id,
            @RequestBody @Valid CarDTO carDTO) {
        Car carSave = carService.updateCarById(id, carDTO);
        CarDTO carResponseDTO = new CarDTO(carSave);
        return ResponseEntity.ok(carResponseDTO);
    }
}
