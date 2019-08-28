package io.gojek.parkinglot.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.gojek.parkinglot.exception.ParkingException;
import io.gojek.parkinglot.model.Car;

public class ParkingDaoImplTest {

	ParkingDao parkingDao = ParkingDaoImpl.getInstance(6);

	@Test
	public void testPark() {
		try {
			assertEquals(1, parkingDao.park(new Car("KA-01-HH-1234", "white")));
			assertEquals(2, parkingDao.park(new Car("KA-01-HH-9999", "white")));
			assertNotEquals(5, parkingDao.park(new Car("KA-01-HH-9899", "red")));
		} catch (ParkingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testUnPark() {
		try {
			assertTrue(parkingDao.unPark(2));
			assertFalse(parkingDao.unPark(5));
		} catch (ParkingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetRegistrationNumbersOfColour() {
		try {
			assertEquals("KA-01-HH-9899", parkingDao.getRegistrationNumbersOfColour("red").get(0));
			assertNotEquals("KA-01-HH-9879", parkingDao.getRegistrationNumbersOfColour("red").get(0));
		} catch (ParkingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetSlotNumbersOfColour() {
		try {
			assertNull(parkingDao.getSlotNumbersOfColour("blue"));
		} catch (ParkingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetSlotNumberFromRegistrationNumber() {
		try {
			assertEquals(1, parkingDao.getSlotNumberFromRegistrationNumber("KA-01-HH-1234"));
		} catch (ParkingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetAvailableSlotsCount() {
		try {
			assertEquals(3, parkingDao.getAvailableSlotsCount());
		} catch (ParkingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
