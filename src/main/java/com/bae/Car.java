package com.bae;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // flags class as a table to spring data
public class Car {

	@Column(nullable = false)
	private String colour;
	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // sets auto increment for ID
	private Integer id;
	@Column(nullable = false)
	private String make;
	@Column(nullable = false)
	private String model;
	@Column(nullable = false)
	private Integer numOfDoors;
	@Column(nullable = false)
	private Integer price;

//	Constructor with ID

	public Car(String colour, Integer id, String make, String model, Integer numOfDoors, Integer price) {
		super();
		this.colour = colour;
		this.id = id;
		this.make = make;
		this.model = model;
		this.numOfDoors = numOfDoors;
		this.price = price;
	}

// Constructor with no ID

	public Car(String colour, String make, String model, Integer numOfDoors, Integer price) {
		super();
		this.colour = colour;
		this.make = make;
		this.model = model;
		this.numOfDoors = numOfDoors;
		this.price = price;
	}

// Default constructor

	public Car() {
		super();
	}

//	Getters and Setters

	public String getColour() {
		return colour;
	}

	public Integer getId() {
		return id;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public Integer getNumOfDoors() {
		return numOfDoors;
	}

	public Integer getPrice() {
		return price;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setNumOfDoors(Integer numOfDoors) {
		this.numOfDoors = numOfDoors;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	// ToString method
	@Override
	public String toString() {
		return "Car [numOfDoors=" + numOfDoors + ", make=" + make + ", model=" + model + ", price=" + price
				+ ", colour=" + colour + ", id=" + id + "]";
	}

}