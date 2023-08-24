package com.example.cars.and.users.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cars.and.users.api.dto.CarDTO;
import com.example.cars.and.users.api.exceptions.LicensePlateAlreadyExistsExeception;
import com.example.cars.and.users.api.exceptions.UserNotFoundException;
import com.example.cars.and.users.api.model.Car;
import com.example.cars.and.users.api.model.User;
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


	public Car createCar(CarDTO carDTO) throws LicensePlateAlreadyExistsExeception {
		Car carModel = new Car();
		if (carDTO != null) {
			carModel.setColor(carDTO.getColor());
			carModel.setLicensePlate(carDTO.getLicensePlate());
			
			// Verifica se a placa  já existe
			if (this.carRepository.existsByLicensePlate(carModel.getLicensePlate())) {
				throw  new LicensePlateAlreadyExistsExeception();
			}
			
			carModel.setModel(carDTO.getModel());
			carModel.setUsuario(carDTO.getUser());
			carModel.setYear(Integer.parseInt(carDTO.getYear()));
		}
		return this.carRepository.save(carModel);
	}

	public Car getCarById(Long id) {

		Optional<Car> car = carRepository.findById(id);

		return car.get();

	}

}
