package com.example.cars.and.users.api.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.cars.and.users.api.model.Car;
import com.example.cars.and.users.api.model.User;

public class UserDTO {

	private String id;

	
	@NotNull(message = "Missing fields!")
	@NotBlank(message = "Invalid fields!")
	private String firstName;

	@NotNull
	private String lastName;

	@NotNull
	private String email;

	@NotNull
	private String birthday;

	@NotNull
	private String login;

	@NotNull
	private String password;

	@NotNull
	private String phone;

	private List<Car> cars;

	public String getId() {
		return id;
	}
	
	public UserDTO() {
		
	}

	  public UserDTO(User user) {
	        this.id = Long.toString(user.getId());
	        this.firstName = user.getFirstName();
	        this.lastName = user.getLastName();
	        this.email = user.getEmail();
	        this.birthday = user.getBirthday();
	        this.login = user.getLogin();
	        this.password = user.getPassword();
	        this.phone = user.getPhone();
	        this.cars = user.getCars();
	    }


	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	
	
}
