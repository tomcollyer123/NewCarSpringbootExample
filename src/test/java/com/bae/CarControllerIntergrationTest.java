package com.bae;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

//boots the entire context - random port to avoid collisions (two apps running on the same port)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // sets up MockMVC object
@Sql(scripts = { "classpath:car-schema.sql",
		"classpath:car-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class CarControllerIntergrationTest {

	@Autowired // pulls the MockMVC object from the context
	private MockMvc mvc; // class that performs the request (like what postman does)

	@Autowired
	private ObjectMapper mapper; // java to JSON converter that spring uses

	@Test
	void testCreate() throws Exception {
		Car testCar = new Car("red", null, "Ferrari", "La Ferrari", 2, 1600000);
		String testCarAsJSON = this.mapper.writeValueAsString(testCar);
		RequestBuilder req = post("/create").content(testCarAsJSON).contentType(MediaType.APPLICATION_JSON);

		Car testCreatedCar = new Car("red", 4, "Ferrari", "La Ferrari", 2, 1600000);
		String testCreatedCarAsJSON = this.mapper.writeValueAsString(testCreatedCar);
		ResultMatcher checkStatus = status().isCreated(); // this is checking the status code
		ResultMatcher checkBody = content().json(testCreatedCarAsJSON); // this is checking the body

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody); // mvc sends the request -
																			// first expect checks the status
																			// second expect checks the body
	}

// ID for both tests is now 2 because car data file creates an object of ID 1 before the tests starts.
	@Test
	void testCreate2() throws Exception {
		Car testCar = new Car("red", null, "Ferrari", "La Ferrari", 2, 1600000);
		String testCarAsJSON = this.mapper.writeValueAsString(testCar);
		RequestBuilder req = post("/create").content(testCarAsJSON).contentType(MediaType.APPLICATION_JSON);

		Car testCreatedCar = new Car("red", 4, "Ferrari", "La Ferrari", 2, 1600000);
		String testCreatedCarAsJSON = this.mapper.writeValueAsString(testCreatedCar);
		ResultMatcher checkStatus = status().isCreated(); // this is checking the status code
		ResultMatcher checkBody = content().json(testCreatedCarAsJSON); // this is checking the body

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void testUpdate() throws Exception {
		Car testCar = new Car("red", null, "Ferrari", "La Ferrari", 2, 1600000);
		String testCarAsJSON = this.mapper.writeValueAsString(testCar);
		RequestBuilder req = post("/create").content(testCarAsJSON).contentType(MediaType.APPLICATION_JSON);

		Car testUpdatedCar = new Car("red", 4, "Ferrari", "La Ferrari", 2, 1600000);
		String testUpdatedCarAsJSON = this.mapper.writeValueAsString(testUpdatedCar);
		ResultMatcher checkStatus = status().isCreated(); // this is checking the status code
		ResultMatcher checkBody = content().json(testUpdatedCarAsJSON); // this is checking the body

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testGetAll() throws Exception {
		List<Car> testCars = List.of(new Car("red", 1, "Ferrari", "La Ferrari", 2, 1600000)
		("blue", 2,"Land Rover","RangeRover sport",5,75000)("yellow", 3,"porsche","GT3 RS",2,130000));
		String json = this.mapper.writeValueAsString(testCars);

		RequestBuilder req = get("/getAll");
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testGetByMake() throws Exception {
		List<Car> testCars = List.of(new Car("red", 1, "Ferrari", "La Ferrari", 2, 1600000));
		String json = this.mapper.writeValueAsString(testCars);

		RequestBuilder req = get("/getByMake/Ferrari");
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testGetByModel() throws Exception {
		List<Car> testCars = List.of(new Car("red", 1, "Ferrari", "La Ferrari", 2, 1600000));
		String json = this.mapper.writeValueAsString(testCars);

		RequestBuilder req = get("/getByModel/La Ferrari");
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testGetByColour() throws Exception {
		List<Car> testCars = List.of(new Car("red", 1, "Ferrari", "La Ferrari", 2, 1600000));
		String json = this.mapper.writeValueAsString(testCars);

		RequestBuilder req = get("/getByColour/red");
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testGetById() throws Exception {
		Car testCar = new Car("red", 1, "Ferrari", "La Ferrari", 2, 1600000);
		String json = this.mapper.writeValueAsString(testCar);

		RequestBuilder req = get("/getById/1");
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

//	@Test
//	void testDelete() throws Exception {
//
//	}
}
