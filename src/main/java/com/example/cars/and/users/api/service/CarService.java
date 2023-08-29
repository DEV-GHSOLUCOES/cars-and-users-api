package com.example.cars.and.users.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cars.and.users.api.dto.CarDTO;
import com.example.cars.and.users.api.exceptions.LicensePlateAlreadyExistsExeception;
import com.example.cars.and.users.api.model.Car;
import com.example.cars.and.users.api.model.User;
import com.example.cars.and.users.api.repository.CarRepository;
import com.example.cars.and.users.api.repository.UserRepository;

@Service
public class CarService {

	@Autowired
	private CarRepository carRepository;

	
	public List<CarDTO> getAllCars() {
		List<Car> listCars = carRepository.findAll();
		List<CarDTO> listCarsDTO = new ArrayList<>();

		for (Car car : listCars) {

			CarDTO carDTO = new CarDTO();
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

			// Verifica se a placa já existe
			if (this.carRepository.existsByLicensePlate(carModel.getLicensePlate())) {
				throw new LicensePlateAlreadyExistsExeception();
			}

			carModel.setModel(carDTO.getModel());
			//carModel.getUsuario().setId(carDTO.getUser().getId());;
			carModel.setYear(Integer.parseInt(carDTO.getYear()));
		}
		return this.carRepository.save(carModel);
	}

	public Car getCarById(Long id) {

		Optional<Car> car = carRepository.findById(id);

		return car.get();

	}

	public boolean canDeleteCar(Long carId) {
		Car car = getCarById(carId);
		User user = car.getUser();

		return user != null ? true : false;
	}

	public void deleteById(Long id) {
		if (canDeleteCar(id)) {
			throw new IllegalStateException();
		}
		carRepository.deleteById(id);

	}

	public Car updateCarById(Long id, CarDTO carDTO) {
		Car carSave = getCarById(id);

		// Verifica se a placa já existe
		if (this.carRepository.existsByLicensePlate(carDTO.getLicensePlate())) {
			throw new LicensePlateAlreadyExistsExeception();
		}

		
		BeanUtils.copyProperties(carDTO, carSave, "id");

		return carRepository.save(carSave);
		
	}
	
	public List<Car> convertCarDTOsToCars(List<CarDTO> carDTOs) {
	    List<Car> cars = new ArrayList<>();
	    
	    for (CarDTO carDTO : carDTOs) {
	        Car car = convertCarDTOToCar(carDTO);
	        cars.add(car);
	    }
	    
	    return cars;
	}

	public Car convertCarDTOToCar(CarDTO carDTO) {
	    Car car = new Car();
	    
	    // Set properties from CarDTO to Car
	    car.setYear(Integer.valueOf(carDTO.getYear()));
	    car.setLicensePlate(carDTO.getLicensePlate());
	    car.setModel(carDTO.getModel());
	    car.setColor(carDTO.getColor());

	    return car;
	}
	
}
