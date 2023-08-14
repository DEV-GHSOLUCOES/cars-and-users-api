package com.example.cars.and.users.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cars.and.users.api.dto.CarDTO;
import com.example.cars.and.users.api.model.Car;
import com.example.cars.and.users.api.repository.CarRepository;

@Service
public class CarService {
	
	@Autowired
    private final CarRepository carRepository;

    
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    
    public List<CarDTO> getAllCars() {
    	 List<Car> listCars =  carRepository.findAll();
    	 List<CarDTO> listCarsDTO =  new  ArrayList<>();
    	 
    	 for (Car car : listCars) {
    		
    		 CarDTO carDTO  = new CarDTO();
    		 carDTO.setColor(car.getColor());
    		 carDTO.setId(Long.toString(car.getId()));
    		 carDTO.setLicensePlate((car.getLicensePlate()));
    		 carDTO.setModel((car.getModel()));
    		 carDTO.setYear(Integer.toString(car.getYear()));
    		 
    		 listCarsDTO.add(carDTO);
			
		}
    	
		return listCarsDTO;
    	
    	
    	
		
	}
    
}
