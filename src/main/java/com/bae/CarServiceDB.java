package com.bae;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class CarServiceDB implements CarService {

	private CarRepo repo;

	@Autowired
	public CarServiceDB(CarRepo repo) {
		super();
		this.repo = repo;

	}

	@Override
	public Car createCar(Car car) {
		Car created = this.repo.save(car); // This is the same as 'insert into' in DB
		return created;
	}

	@Override
	public List<Car> getAllCars() {

		return this.repo.findAll(); // same as SELECT * from car...
	}

	@Override
	public Car getCar(Integer id) {
		Optional<Car> found = this.repo.findById(id);
		return found.get();
	}

	@Override
	public Car replaceCar(Integer id, Car newCar) {
		Car existing = this.repo.getById(id);
		existing.setColour(newCar.getColour());
		existing.setMake(newCar.getMake());
		existing.setModel(newCar.getModel());
		existing.setNumOfDoors(newCar.getNumOfDoors());
		existing.setPrice(newCar.getPrice());
		Car updated = this.repo.save(existing);
		return updated;
	}

	@Override
	public List<Car> getAllCarsByMake(String make) {
		List<Car> found = this.repo.findByMakeIgnoreCase(make);
		return found;
	}

	@Override
	public List<Car> getAllCarsByModel(String model) {
		List<Car> found = this.repo.findByModelIgnoreCase(model);
		return found;
	}

	@Override
	public void removeCar(Integer id) {
		this.repo.deleteById(id); // same as DELETE from car where id =

	}

	@Override
	public List<Car> getAllCarsByColour(String colour) {
		List<Car> found = this.repo.findByColourIgnoreCase(colour);
		return found;
	}

}