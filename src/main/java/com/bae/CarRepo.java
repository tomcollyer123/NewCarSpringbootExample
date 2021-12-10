package com.bae;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepo extends JpaRepository<Car, Integer> {

	List<Car> findByMakeIgnoreCase(String make);

	List<Car> findByModelIgnoreCase(String model);

	List<Car> findByNumOfDoors(Integer numOfDoors);

	List<Car> findByPrice(Integer price);

	List<Car> findByColourIgnoreCase(String colour);

	List<Car> findByPriceIgnoreCase(Number price);

}