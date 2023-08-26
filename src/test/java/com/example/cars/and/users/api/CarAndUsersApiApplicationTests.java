package com.example.cars.and.users.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = CarAndUserApiApplication.class)
class CarAndUsersApiApplicationTests {

	
	  @Test 
	  void contextLoads() { 
		  
	  }
		/*
		 * @Test public void testApiRestCreateUser() throws JsonProcessingException,
		 * Exception { DefaultMockMvcBuilder builder =
		 * MockMvcBuilders.webAppContextSetup(this.applicationContext); MockMvc mockMvc
		 * = builder.build();
		 * 
		 * UserDTO user = new UserDTO(); user.setFirstName("GUSTAVO");
		 * user.setLastName("SANTOS"); user.setEmail("gustavo@example.com");
		 * user.setBirthday("1990-01-15"); user.setLogin("johndoe");
		 * user.setPassword("password1234"); user.setPhone("123-456-7890");
		 * user.setCars(new ArrayList<Car>());
		 * 
		 * ResultActions retornoApi = mockMvc
		 * .perform(MockMvcRequestBuilders.post("/api/users/create")
		 * .content(objectMapper.writeValueAsString(user))
		 * .accept(MediaType.APPLICATION_JSON)
		 * .contentType(MediaType.APPLICATION_JSON)); }
		 */
	 

}
