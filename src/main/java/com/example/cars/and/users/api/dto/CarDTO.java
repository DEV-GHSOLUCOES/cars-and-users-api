package com.example.cars.and.users.api.dto;

import com.example.cars.and.users.api.model.Car;
import com.example.cars.and.users.api.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class CarDTO {

	private String id;

	private String year;

	private String licensePlate;

	private String model;

	private String color;

	@JsonIgnore
	private User user;

	public CarDTO() {

	}

	public CarDTO(Car car) {
		this.id = Long.toString(car.getId());
		this.year = String.valueOf(car.getYear());
		this.licensePlate = car.getLicensePlate();
		this.model = car.getModel();
		this.color = car.getColor();
		this.user = car.getUser();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	/*
	 * public User getUser() { return user; }
	 * 
	 * public void setUser(User user) { this.user = user; }
	 */

}
