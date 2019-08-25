package io.gojek.parkinglot.service;

import java.util.List;

import io.gojek.parkinglot.exception.ParkingException;
import io.gojek.parkinglot.model.Vehicle;

/**
 * @author Mandeep Singh
 *
 */
public interface ParkingService {

	public void createParkingLot(int size) throws ParkingException;

	public boolean park(Vehicle vehicle) throws ParkingException;

	public void unPark(int slotNumber) throws ParkingException;

	public void getStatus() throws ParkingException;

	public List<String> getRegistrationNumbersOfColour(String colour) throws ParkingException;

	public List<Integer> getSlotNumbersOfColour(String colour) throws ParkingException;

	public int getSlotNumberFromRegistrationNumber(String registrationNumber) throws ParkingException;

	public Integer getAvailableSlotsCount() throws ParkingException;

	public void doCleanup();
}
