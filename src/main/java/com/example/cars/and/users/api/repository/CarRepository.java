package com.example.cars.and.users.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cars.and.users.api.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

	 public boolean existsByLicensePlate(String licensePlate);
    
}
