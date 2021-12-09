package com.bae;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

//boots the entire context - random port to avoid collisions (two apps running on the same port)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // sets up MockMVC object
public class CarControllerIntergrationTest {

	@Autowired // pulls the MockMVC object from the context
	private MockMvc mvc; // class that performs the request (like what postman does)

	@Autowired
	private ObjectMapper mapper; // java to JSON converter that spring uses

	@Test
	void testCreate() throws Exception {
		Car testCar = new Car("red", null, "Ferrari", "La Ferarri", 1600000, 5);
		String testCarAsJSON = this.mapper.writeValueAsString(testCar);
		RequestBuilder req = post("/create").content(testCarAsJSON).contentType(MediaType.APPLICATION_JSON);

		Car testCreatedCar = new Car("red", 1, "Ferrari", "La Ferarri", 1600000, 5);
		String testCreatedCarAsJSON = this.mapper.writeValueAsString(testCreatedCar);
		ResultMatcher checkStatus = status().isCreated(); // this is checking the status code
		ResultMatcher checkBody = content().json(testCreatedCarAsJSON); // this is checking the body

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody); // mvc sends the request -
																			// first expect checks the status
																			// second expect checks the body
	}

}
