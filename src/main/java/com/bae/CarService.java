package com.bae;

import java.util.List;

public interface CarService {

//		CRUD Functionality

	Car createCar(Car car);

	List<Car> getAllCars();

	List<Car> getAllCarsByMake(String type);

	List<Car> getAllCarsByModel(String type);

	List<Car> getAllCarsByColour(String colour);

	List<Car> getAllCarsByPrice(Number price);

	Car getCar(Integer id);

	Car replaceCar(Integer id, Car newCar);

	void removeCar(Integer id);

}