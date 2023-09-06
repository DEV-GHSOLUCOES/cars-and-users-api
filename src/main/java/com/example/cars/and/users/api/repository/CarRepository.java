package com.example.cars.and.users.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cars.and.users.api.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

	 public Car findFirstByLicensePlate(String licensePlate);
	 
    
}
