package io.gojek.parkinglot.model;

import io.gojek.parkinglot.constants.Colour;

/**
 * @author Mandeep Singh
 *
 */
public abstract class Vehicle {

	private String registrationNumber;
	private String colour;

	public Vehicle(String registrationNumber, String colour) {
		super();
		this.registrationNumber = registrationNumber;
		this.colour = colour;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public static boolean isRegistrationNumber(String input) {
		String regexPattern = "^[A-Z]{2}[-][0-9]{2}[-][A-Z]{2}[-][0-9]{4}$";
		return input.matches(regexPattern);
	}

	public static boolean isColour(String input) {
		return Colour.contains(input);
	}
}
