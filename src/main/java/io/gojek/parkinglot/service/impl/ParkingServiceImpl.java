package io.gojek.parkinglot.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringJoiner;

import io.gojek.parkinglot.constants.ExceptionCodes;
import io.gojek.parkinglot.constants.ExceptionMessages;
import io.gojek.parkinglot.dao.ParkingDao;
import io.gojek.parkinglot.dao.ParkingDaoImpl;
import io.gojek.parkinglot.exception.ParkingException;
import io.gojek.parkinglot.model.Slot;
import io.gojek.parkinglot.model.Vehicle;
import io.gojek.parkinglot.service.ParkingService;

/**
 * @author Mandeep Singh
 *
 */
public class ParkingServiceImpl implements ParkingService {

	private ParkingDao parkingDao = null;

	@Override
	public void createParkingLot(int size) throws ParkingException {
		if (this.parkingDao != null) {
			throw new ParkingException(ExceptionMessages.PARKING_ALREADY_EXIST.getMessage());
		} else {
			this.parkingDao = ParkingDaoImpl.getInstance(size);
		}
		System.out.println("Created a parking lot with " + size + " slots");
	}

	@Override
	public void park(Vehicle vehicle) throws ParkingException {
		int slotNumber = this.parkingDao.park(vehicle);
		if (slotNumber == ExceptionCodes.NOT_AVAILABLE) {
			System.out.println(ExceptionMessages.PARKING_LOT_FULL.getMessage());
//			throw new ParkingException(ExceptionMessages.PARKING_LOT_FULL.getMessage());
		} else if (slotNumber == ExceptionCodes.VEHICLE_ALREADY_EXIST) {
			System.out.println(ExceptionMessages.VEHICLE_ALREADY_EXIST.getMessage());
		} else {
			System.out.println("Allocated slot number: " + slotNumber);
		}
	}

	@Override
	public void unPark(int slotNumber) throws ParkingException {
		boolean unparked = this.parkingDao.unPark(slotNumber);
		if (unparked) {
			System.out.println("Slot number " + slotNumber + " is free");
		} else {
			System.out.println(ExceptionMessages.PARKING_NOT_EXIST_ERROR.getMessage());
//			throw new ParkingException(ExceptionMessages.PARKING_NOT_EXIST_ERROR.getMessage());
		}
	}

	@Override
	public void getStatus() throws ParkingException {
		Map<Slot, Vehicle> lot = this.parkingDao.getStatus();
		System.out.println("Slot No." + "\t" + "Registration No" + "\t\t" + "Colour");
		for (Entry<Slot, Vehicle> entry : lot.entrySet()) {
			System.out.println(entry.getKey().getSlotNumber() + "\t\t" + entry.getValue().getRegistrationNumber()
					+ "\t\t" + entry.getValue().getColour());
		}
	}

	@Override
	public List<String> getRegistrationNumbersOfColour(String colour) throws ParkingException {
		List<String> regNumbers = this.parkingDao.getRegistrationNumbersOfColour(colour);
		if (regNumbers == null) {
			System.out.println(ExceptionMessages.NOT_FOUND.getMessage());
//			throw new ParkingException(ExceptionMessages.NOT_FOUND.getMessage());
		} else {
			System.out.println(String.join(",", regNumbers));
		}
		return regNumbers;
	}

	@Override
	public List<Integer> getSlotNumbersOfColour(String colour) throws ParkingException {
		List<Integer> slotNumbers = this.parkingDao.getSlotNumbersOfColour(colour);
		if (slotNumbers == null) {
			System.out.println(ExceptionMessages.NOT_FOUND.getMessage());
//			throw new ParkingException(ExceptionMessages.NOT_FOUND.getMessage());
		} else {
			StringJoiner joiner = new StringJoiner(",");
			for (Integer slotNumber : slotNumbers) {
				joiner.add(slotNumber + "");
			}
			System.out.println(joiner.toString());
		}
		return slotNumbers;
	}

	@Override
	public int getSlotNumberFromRegistrationNumber(String registrationNumber) throws ParkingException {
		int slotNumber = this.parkingDao.getSlotNumberFromRegistrationNumber(registrationNumber);
		if (slotNumber == ExceptionCodes.NOT_FOUND) {
			System.out.println(ExceptionMessages.NOT_FOUND.getMessage());
//			throw new ParkingException(ExceptionMessages.NOT_FOUND.getMessage());
		} else {
			System.out.println(slotNumber);
		}
		return slotNumber;
	}

	@Override
	public Integer getAvailableSlotsCount() throws ParkingException {
		return this.parkingDao.getAvailableSlotsCount();
	}

}
