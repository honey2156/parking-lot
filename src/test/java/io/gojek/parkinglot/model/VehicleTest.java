package io.gojek.parkinglot.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class VehicleTest {

	@Test
	public void testIsRegistrationNumber() {
		assertEquals(true, Vehicle.isRegistrationNumber("KA-01-HH-3141"));
		assertEquals(false, Vehicle.isRegistrationNumber("KA-01-HH-31PP"));
		assertEquals(true, Vehicle.isRegistrationNumber("KA-01-BB-0001"));
	}

	@Test
	public void testIsColour() {
		assertEquals(true, Vehicle.isColour("red"));
		assertEquals(true, Vehicle.isColour("whiTe"));
		assertEquals(false, Vehicle.isColour("blackk"));
	}

}
