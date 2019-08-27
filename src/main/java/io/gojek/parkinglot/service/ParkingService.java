package io.gojek.parkinglot.service;

import java.util.List;

import io.gojek.parkinglot.exception.ParkingException;
import io.gojek.parkinglot.model.Vehicle;

/**
 * @author Mandeep Singh
 *
 */
public interface ParkingService {

	/**
	 * Create parking lot of given size
	 * 
	 * @param size
	 * @throws ParkingException
	 */
	public void createParkingLot(int size) throws ParkingException;

	/**
	 * Park vehicle into parking lot
	 * 
	 * @param vehicle
	 * @throws ParkingException
	 */
	public void park(Vehicle vehicle) throws ParkingException;

	/**
	 * Unpark vehicle from parking lot
	 * 
	 * @param slotNumber
	 * @throws ParkingException
	 */
	public void unPark(int slotNumber) throws ParkingException;

	/**
	 * Display status of parking lot. Provides layout of parking lot with vehicles
	 * parked in their respective slots
	 * 
	 * @throws ParkingException
	 */
	public void getStatus() throws ParkingException;

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
	public Integer getAvailableSlotsCount() throws ParkingException;

}
