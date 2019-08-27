package io.gojek.parkinglot.service.impl;

import java.util.List;

import io.gojek.parkinglot.exception.ParkingException;
import io.gojek.parkinglot.model.Vehicle;
import io.gojek.parkinglot.service.ParkingService;

/**
 * @author Mandeep Singh
 *
 */
public class ParkingServiceImpl implements ParkingService {

	@Override
	public void createParkingLot(int size) throws ParkingException {
		// TODO Auto-generated method stub

	}

	@Override
	public void park(Vehicle vehicle) throws ParkingException {
		// TODO Auto-generated method stub

	}

	@Override
	public void unPark(int slotNumber) throws ParkingException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getStatus() throws ParkingException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> getRegistrationNumbersOfColour(String colour) throws ParkingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getSlotNumbersOfColour(String colour) throws ParkingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSlotNumberFromRegistrationNumber(String registrationNumber) throws ParkingException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Integer getAvailableSlotsCount() throws ParkingException {
		// TODO Auto-generated method stub
		return null;
	}

}
