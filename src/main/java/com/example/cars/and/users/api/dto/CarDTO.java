package com.example.cars.and.users.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.cars.and.users.api.model.Car;
import com.example.cars.and.users.api.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class CarDTO {
	
	
	@JsonIgnore
    private String id;
    
    @NotNull(message = "Missing fields!")
	@NotBlank(message = "Invalid fields!")
    private String year;
    
    @NotNull(message = "Missing fields!")
	@NotBlank(message = "Invalid fields!")
    private String licensePlate;
    
    @NotNull(message = "Missing fields!")
	@NotBlank(message = "Invalid fields!")
    private String model;
    
    @NotNull(message = "Missing fields!")
	@NotBlank(message = "Invalid fields!")
    private String color;
    
    
	/*
	 * @JsonIgnore private User user;
	 */
    
    public CarDTO() {
		
	}

	
	public CarDTO(Car car) {
		this.id = Long.toString(car.getId());
		this.year = String.valueOf(car.getYear());
		this.licensePlate = car.getLicensePlate();
		this.model = car.getModel();
		this.color = car.getColor();
		//this.user = car.getUsuario();
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

