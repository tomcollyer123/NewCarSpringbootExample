package com.bae;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // tells spring this is a controller
				// REST complaint
public class CarController {

	private CarService service;

	@Autowired // tells spring to fetch the CarService from the context // dependency injection
	public CarController(CarService service) {
		super();
		this.service = service;
	}

	private List<Car> car = new ArrayList<>();

	@GetMapping("/helloWorld") // end point
	public String hello() {
		return "Hello, World";
	}

//	CRUD Functionality

	@PostMapping("/create") // Post because we are CREATING // 201 - created
	public ResponseEntity<Car> createCar(@RequestBody Car car) {
		Car created = this.service.createCar(car);
		ResponseEntity<Car> response = new ResponseEntity<Car>(created, HttpStatus.CREATED);
		// this gives back a status code of 201
		return response; // This will return the created new car and a response code of 201
	}

	@GetMapping("/getByMake/{make}")
	public ResponseEntity<List<Car>> getCarByMake(@PathVariable String make) {
		List<Car> found = this.service.getAllCarsByMake(make);
		return ResponseEntity.ok(found);
	}

	@GetMapping("/getByModel/{model}")
	public ResponseEntity<List<Car>> getCarByModel(@PathVariable String model) {
		List<Car> found = this.service.getAllCarsByModel(model);
		return ResponseEntity.ok(found);
	}

	@GetMapping("/getByColour/{colour}")
	public ResponseEntity<List<Car>> getCarByColour(@PathVariable String colour) {
		List<Car> found = this.service.getAllCarsByColour(colour);
		return ResponseEntity.ok(found);
	}

	@GetMapping("/getAll") // get because we are READING something // 200
	// 200 - don't need to change these as the default is 200 anyway
	public ResponseEntity<List<Car>> getAllCars() {
		return ResponseEntity.ok(this.service.getAllCars()); // This will return the full list of cars
	}

	@GetMapping("get/{id}") // get because we are reading information // 200
	// 200 - don't need to change these as the default is 200 anyway
	public Car getCar(@PathVariable Integer id) { // @pathvariable is telling spring
//		that the id is being pulled from above
		return this.service.getCar(id);
	}

	@PutMapping("/replace/{id}") // put because we are replacing the whole record (UPDATING) // 202
	public ResponseEntity<Car> replaceCar(@PathVariable Integer id, @RequestBody Car newCar) {
//@pathvariable is so you can pull id from above
//@requestbody so you can get new car info
		Car body = this.service.replaceCar(id, newCar);
		ResponseEntity<Car> response = new ResponseEntity<Car>(body, HttpStatus.ACCEPTED);

		return response;
	}

	@DeleteMapping("/remove/{id}") // DELETING // 204
	public ResponseEntity<Car> removeCar(@PathVariable Integer id) {
		this.service.removeCar(id);

		return new ResponseEntity<Car>(HttpStatus.NO_CONTENT);
	}
}