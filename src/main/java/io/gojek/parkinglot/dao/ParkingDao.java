package io.gojek.parkinglot.dao;

import java.util.List;
import java.util.Map;

import io.gojek.parkinglot.exception.ParkingException;
import io.gojek.parkinglot.model.Slot;
import io.gojek.parkinglot.model.Vehicle;

/**
 * @author Mandeep Singh
 *
 */
public interface ParkingDao {
	
	public boolean park(Vehicle vehicle) throws ParkingException;

	public void unPark(int slotNumber) throws ParkingException;

	public Map<Slot, Vehicle> getStatus() throws ParkingException;

	public List<String> getRegistrationNumbersOfColour(String colour) throws ParkingException;

	public List<Integer> getSlotNumbersOfColour(String colour) throws ParkingException;

	public int getSlotNumberFromRegistrationNumber(String registrationNumber) throws ParkingException;

	public Integer getAvailableSlotsCount() throws ParkingException;

}
