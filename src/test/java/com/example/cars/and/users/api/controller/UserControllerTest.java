package com.example.cars.and.users.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.cars.and.users.api.dto.UserDTO;
import com.example.cars.and.users.api.model.Car;
import com.example.cars.and.users.api.model.User;
import com.example.cars.and.users.api.repository.UserRepository;
import com.example.cars.and.users.api.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
public class UserControllerTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserController userController;

	@Autowired
	private UserService userService;
	
	@Autowired
	private WebApplicationContext applicationContext;

	ObjectMapper objectMapper = new ObjectMapper();

	/*@Test
	public void listUsersTest() throws Exception {
		List<UserDTO> allUsers = Arrays.asList(userDTO);

		allUsers = userService.getAllUsers();

		assertEquals(true, !allUsers.get(0).getId().isEmpty());
	}*/

	@Test
	@DisplayName("Teste de sucesso criacao de usuario")
	public void testApiRestCreateUser201() throws JsonProcessingException, Exception {
		DefaultMockMvcBuilder builder  =  MockMvcBuilders.webAppContextSetup(this.applicationContext);
		MockMvc mockMvc = builder.build();
		
		UserDTO user = new UserDTO();
		user.setFirstName("GUSTAVO");
		user.setLastName("SANTOS");
		user.setEmail("gustavo.santos2023@example.com");
		user.setBirthday("1990-01-15");
		user.setLogin("gustavo.santos2023");
		user.setPassword("password1234");
		user.setPhone("123-456-7890");
		user.setCars(new ArrayList<Car>());
		
		ResultActions returnApi = mockMvc
									.perform(MockMvcRequestBuilders.post("/api/users/create")
									.content(objectMapper.writeValueAsString(user))
									.accept(MediaType.APPLICATION_JSON)
									.contentType(MediaType.APPLICATION_JSON));
		
		/*Converter o reotorno da api para um obj de user*/
		System.out.println("RETORNO DA API " + returnApi.andReturn().getResponse().getContentAsString());
		System.out.println("STATUS " + returnApi.andReturn().getResponse().getStatus());
		UserDTO objetoReturnApi =  objectMapper.readValue(returnApi.andReturn().getResponse().getContentAsString(), 
												UserDTO.class);
		
		assertEquals(user.getFirstName(), objetoReturnApi.getFirstName());
		assertEquals(user.getLogin(), objetoReturnApi.getLogin());
		assertEquals(201, returnApi.andReturn().getResponse().getStatus());
	}
	
	@Test
	@DisplayName("Teste de falha criacao de usuario")
	public void testApiRestCreateUser400() throws JsonProcessingException, Exception {
		DefaultMockMvcBuilder builder  =  MockMvcBuilders.webAppContextSetup(this.applicationContext);
		MockMvc mockMvc = builder.build();
		
		UserDTO user = new UserDTO();
		user.setFirstName("GUSTAVO");
		user.setLastName("SANTOS");
		user.setEmail("gustavo@example.com");
		user.setBirthday("1990-01-15");
		user.setLogin("gustavo.santos");
		user.setPassword("password1234");
		user.setPhone("123-456-7890");
		user.setCars(new ArrayList<Car>());
		
		ResultActions returnApi = mockMvc
									.perform(MockMvcRequestBuilders.post("/api/users/create")
									.content(objectMapper.writeValueAsString(user))
									.accept(MediaType.APPLICATION_JSON)
									.contentType(MediaType.APPLICATION_JSON));
		
		/*Converter o reotorno da api para um obj de user*/
		System.out.println("RETORNO DA API " + returnApi.andReturn().getResponse().getContentAsString());
		System.out.println("STATUS " + returnApi.andReturn().getResponse().getStatus());
		
		ErrorResponse[] errorResponse = objectMapper.readValue(returnApi.andReturn().getResponse().getContentAsString(), ErrorResponse[].class);
		
		
        assertEquals("Email already exists.", errorResponse[0].getMessage());
        assertEquals("400 BAD_REQUEST", errorResponse[0].getErrorCode());
	}
	

	@Test
	@DisplayName("Teste de sucesso delete  usuario")
	public void testApiRestdeleteUserById204() throws JsonProcessingException, Exception {
		DefaultMockMvcBuilder builder  =  MockMvcBuilders.webAppContextSetup(this.applicationContext);
		MockMvc mockMvc = builder.build();
		
		UserDTO userDTO = new UserDTO();
		userDTO.setFirstName("GUSTAVO");
		userDTO.setLastName("farias");
		userDTO.setEmail("gustavo.farias@example.com");
		userDTO.setBirthday("1990-01-15");
		userDTO.setLogin("gustavo.farias");
		userDTO.setPassword("password1234");
		userDTO.setPhone("123-456-7890");
		userDTO.setCars(new ArrayList<Car>());
		
		User user = this.userService.userDtoParseUserModel(userDTO);
		this.userRepository.save(user);
		
		
		ResultActions returnApi = mockMvc
									.perform(MockMvcRequestBuilders.delete("/api/users/delete/" + user.getId())
									.content(objectMapper.writeValueAsString(userDTO))
									.accept(MediaType.APPLICATION_JSON)
									.contentType(MediaType.APPLICATION_JSON));
		
		/*Converter o reotorno da api para um obj de user*/
		System.out.println("RETORNO DA API " + returnApi.andReturn().getResponse().getContentAsString());
		System.out.println("STATUS " + returnApi.andReturn().getResponse().getStatus());
			
		
		assertEquals(204, returnApi.andReturn().getResponse().getStatus());
	}
	
	@Test
	@DisplayName("Teste de sucesso buscar usuario por id")
	public void testApiRestGetUserById200() throws JsonProcessingException, Exception {
		DefaultMockMvcBuilder builder  =  MockMvcBuilders.webAppContextSetup(this.applicationContext);
		MockMvc mockMvc = builder.build();
		
		UserDTO userDTO = new UserDTO();
		userDTO.setFirstName("GUSTAVO");
		userDTO.setLastName("SANTOS");
		userDTO.setEmail("gustavo.santos@example.com");
		userDTO.setBirthday("1990-01-15");
		userDTO.setLogin("gustavo.santos");
		userDTO.setPassword("password1234");
		userDTO.setPhone("123-456-7890");
		userDTO.setCars(new ArrayList<Car>());
		
		
		User user = this.userService.userDtoParseUserModel(userDTO);
		this.userRepository.save(user);
		
		
		ResultActions returnApi = mockMvc
									.perform(MockMvcRequestBuilders.get("/api/users/getUserById/" + user.getId())
									.content(objectMapper.writeValueAsString(userDTO))
									.accept(MediaType.APPLICATION_JSON)
									.contentType(MediaType.APPLICATION_JSON));
		
		/*Converter o reotorno da api para um obj de user*/
		System.out.println("RETORNO DA API " + returnApi.andReturn().getResponse().getContentAsString());
		System.out.println("STATUS " + returnApi.andReturn().getResponse().getStatus());
		
		UserDTO returnUser =  objectMapper.readValue(returnApi.andReturn().getResponse().getContentAsString(), 
				UserDTO.class);
		
		assertEquals(200, returnApi.andReturn().getResponse().getStatus());
		
		assertEquals(returnUser.getFirstName(), user.getFirstName());
		
		
		
	}
	
	@Test
	@DisplayName("Teste de sucesso na busca dos  usuarios")
	public void testApiRestListUsers200() throws JsonProcessingException, Exception {
		DefaultMockMvcBuilder builder  =  MockMvcBuilders.webAppContextSetup(this.applicationContext);
		MockMvc mockMvc = builder.build();
		
		UserDTO user = new UserDTO();
		user.setFirstName("GUSTAVO");
		user.setLastName("SANTOS");
		user.setEmail("gustavo.santos2023@example.com");
		user.setBirthday("1990-01-15");
		user.setLogin("gustavo.santos2023");
		user.setPassword("password1234");
		user.setPhone("123-456-7890");
		user.setCars(new ArrayList<Car>());
		
		ResultActions returnApi = mockMvc
									.perform(MockMvcRequestBuilders.get("/api/users")
									.content(objectMapper.writeValueAsString(user))
									.accept(MediaType.APPLICATION_JSON)
									.contentType(MediaType.APPLICATION_JSON));
		
		/*Converter o reotorno da api para um obj de user*/
		System.out.println("RETORNO DA API " + returnApi.andReturn().getResponse().getContentAsString());
		System.out.println("STATUS " + returnApi.andReturn().getResponse().getStatus());
		
		
		assertEquals(200, returnApi.andReturn().getResponse().getStatus());
		
		List<User> list =  objectMapper.readValue(returnApi.andReturn().getResponse().getContentAsString(),
				new TypeReference<List<User>>() {});
		
		assertEquals(7, list.size() -1);
		assertEquals(user.getFirstName(), list.get(0).getFirstName());
		
		//userRepository.deleteById(Long.valueOf(user.getId()));
		
	}
	
	
	
	 
}