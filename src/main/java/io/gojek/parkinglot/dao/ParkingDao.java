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

	/**
	 * Park vehicle by allocating it slot nearest to entry point
	 * 
	 * @param vehicle
	 * @return
	 * @throws ParkingException
	 */
	public int park(Vehicle vehicle) throws ParkingException;

	/**
	 * Unpark vehicle from slot with given slot number
	 * 
	 * @param slotNumber
	 * @return
	 * @throws ParkingException
	 */
	public boolean unPark(int slotNumber) throws ParkingException;

	/**
	 * Get status of parking lot. Provides layout of parking lot with vehicles
	 * parked in their respective slots
	 * 
	 * @return
	 * @throws ParkingException
	 */
	public Map<Slot, Vehicle> getStatus() throws ParkingException;

	/**
	 * Returns registration numbers of vehicles by colour of vehicles
	 * 
	 * @param colour
	 * @return
	 * @throws ParkingException
	 */
	public List<String> getRegistrationNumbersOfColour(String colour) throws ParkingException;

	/**
	 * Returns slot numbers of parking lot allocated with vehicles by colour of
	 * vehicles
	 * 
	 * @param colour
	 * @return
	 * @throws ParkingException
	 */
	public List<Integer> getSlotNumbersOfColour(String colour) throws ParkingException;

	/**
	 * Returns slot numbers of parking lot by registration number of vehicles
	 * 
	 * @param registrationNumber
	 * @return
	 * @throws ParkingException
	 */
	public int getSlotNumberFromRegistrationNumber(String registrationNumber) throws ParkingException;

	/**
	 * Returns available slots for parking
	 * 
	 * @return
	 * @throws ParkingException
	 */
	public int getAvailableSlotsCount() throws ParkingException;

}
