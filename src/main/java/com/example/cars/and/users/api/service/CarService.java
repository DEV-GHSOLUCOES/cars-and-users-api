package com.example.cars.and.users.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.cars.and.users.api.dto.CarDTO;
import com.example.cars.and.users.api.exceptions.LicensePlateAlreadyExistsExeception;
import com.example.cars.and.users.api.exceptions.LoginAlreadyExistsException;
import com.example.cars.and.users.api.model.Car;
import com.example.cars.and.users.api.model.User;
import com.example.cars.and.users.api.repository.CarRepository;
import com.example.cars.and.users.api.repository.UserRepository;
import com.example.cars.and.users.api.security.JwtApiAutenticacaoFilter;
import com.example.cars.and.users.api.security.JwtTokenAutenticacaoService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class CarService {

	@Autowired
	private CarRepository carRepository;

	@Autowired
	UserRepository userRepository;

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

	public Car createCar(CarDTO carDTO, HttpServletRequest request, HttpServletResponse response)
			throws LicensePlateAlreadyExistsExeception {

		JwtTokenAutenticacaoService autenticacaoService = new JwtTokenAutenticacaoService();
		Authentication authentication = autenticacaoService.getAuthentication(request, response);
		String login = (String) authentication.getName();
		System.out.println(login);
		User user = userRepository.findFirstByLogin(login);
		Car carModel = new Car();

		if (carDTO != null) {
			carModel.setColor(carDTO.getColor());
			carModel.setLicensePlate(carDTO.getLicensePlate());

			// Verifica se a placa já existe
			Car existingCarWithPlate = this.carRepository.findFirstByLicensePlate(carModel.getLicensePlate());
			if (existingCarWithPlate != null && !existingCarWithPlate.getId().equals(carModel.getId())) {
				throw new LicensePlateAlreadyExistsExeception();
			}

			carModel.setModel(carDTO.getModel());
			carModel.setUser(user);
			carModel.setYear(Integer.parseInt(carDTO.getYear()));
		}
		return this.carRepository.save(carModel);
	}

	public Car getCarById(Long id) {

		Optional<Car> car = carRepository.findById(id);

		return car.get();

	}

	public void deleteById(Long id) {
		Car car = getCarById(id);
		car.setUser(null);
		carRepository.deleteById(id);

	}

	public Car updateCarById(Long id, CarDTO carDTO) {
		Car carSave = getCarById(id);

		// Verifica se a placa já existe
		Car existingCarWithPlate = this.carRepository.findFirstByLicensePlate(carDTO.getLicensePlate());
		if (existingCarWithPlate != null && !existingCarWithPlate.getId().equals(id)) {
			throw new LicensePlateAlreadyExistsExeception();
		}

		BeanUtils.copyProperties(carDTO, carSave, "id");

		 carSave = carRepository.save(carSave);

		 return carSave;
	}

}
