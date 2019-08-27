package io.gojek.parkinglot.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.gojek.parkinglot.constants.ExceptionMessages;
import io.gojek.parkinglot.exception.ParkingException;
import io.gojek.parkinglot.model.Slot;
import io.gojek.parkinglot.model.Vehicle;

/**
 * @author Mandeep Singh
 *
 */
public class ParkingDaoImpl implements ParkingDao {

	private int parkingLotSize;

	private Map<Slot, Vehicle> parkingLotMap;

	private Map<String, List<String>> registrationNumbersByColour;

	private Map<String, List<Slot>> slotNumbersByColour;

	private Map<String, Integer> slotNumberByRegistrationNumber;

	private List<Integer> emptySlots;

	private static ParkingDaoImpl instance = null;

	private ParkingDaoImpl(int parkingLotSize) {
		super();
		this.parkingLotSize = parkingLotSize;
		this.parkingLotMap = new HashMap<>(this.parkingLotSize);
		this.registrationNumbersByColour = new HashMap<>();
		this.slotNumbersByColour = new HashMap<>();
		this.slotNumberByRegistrationNumber = new HashMap<>();
		this.emptySlots = new ArrayList<>();
		for (int i = 1; i <= this.parkingLotSize; i++) {
			emptySlots.add(i);
		}
	}

	private ParkingDaoImpl(int parkingLotSize, Map<Slot, Vehicle> parkingLotMap,
			Map<String, List<String>> registrationNumbersByColour, Map<String, List<Slot>> slotNumbersByColour,
			Map<String, Integer> slotNumberByRegistrationNumber, List<Integer> emptySlots) {
		super();
		this.parkingLotSize = parkingLotSize;
		this.parkingLotMap = parkingLotMap;
		this.registrationNumbersByColour = registrationNumbersByColour;
		this.slotNumbersByColour = slotNumbersByColour;
		this.slotNumberByRegistrationNumber = slotNumberByRegistrationNumber;
		this.emptySlots = emptySlots;
	}

	private static ParkingDao getInstance(int size) {
		if (instance == null) {
			instance = new ParkingDaoImpl(size);
		}
		return instance;
	}

	@Override
	public boolean park(Vehicle vehicle) throws ParkingException {
		if (emptySlots.size() == 0) {
			throw new ParkingException(ExceptionMessages.PARKING_LOT_FULL.getMessage());
		}
		Collections.sort(this.emptySlots);
		// Get nearest Parking Slot
		int nearestSlot = this.emptySlots.get(0);
		// Park vehicle into nearest slot
		this.parkingLotMap.put(new Slot(nearestSlot), vehicle);

		// update registrationNumbersByColour with new vehicle details
		List<String> registerationNumbers;
		if (this.registrationNumbersByColour.containsKey(vehicle.getColour())) {
			registerationNumbers = this.registrationNumbersByColour.get(vehicle.getColour());
		} else {
			registerationNumbers = new ArrayList<String>();
		}
		registerationNumbers.add(vehicle.getRegistrationNumber());
		this.registrationNumbersByColour.put(vehicle.getColour(), registerationNumbers);

		// update slotNumbersByColour with vehicle details
		List<Slot> slotNumbers;
		if (this.slotNumbersByColour.containsKey(vehicle.getColour())) {
			slotNumbers = this.slotNumbersByColour.get(vehicle.getColour());
		} else {
			slotNumbers = new ArrayList<Slot>();
		}
		slotNumbers.add(new Slot(nearestSlot));
		this.slotNumbersByColour.put(vehicle.getColour(), slotNumbers);

		// update slotNumberByRegistrationNumber
		this.slotNumberByRegistrationNumber.put(vehicle.getRegistrationNumber(), nearestSlot);

		// remove empty slot
		this.emptySlots.remove(0);

		return false;
	}

	@Override
	public void unPark(int slotNumber) throws ParkingException {
		Slot slot = new Slot(slotNumber);

		if (this.parkingLotMap.containsKey(slot) && this.parkingLotMap.get(slot) != null) {
			// unPark vehicle from slot
			Vehicle vehicle = this.parkingLotMap.get(slot);

			// update registrationNumbersByColour with unparked vehicle details
			List<String> registerationNumbers = this.registrationNumbersByColour.get(vehicle.getColour());
			registerationNumbers.remove(vehicle.getRegistrationNumber());
			this.registrationNumbersByColour.put(vehicle.getColour(), registerationNumbers);

			// update slotNumbersByColour with unparked vehicle details
			List<Slot> slotNumbers = this.slotNumbersByColour.get(vehicle.getColour());
			slotNumbers.remove(slot);
			this.slotNumbersByColour.put(vehicle.getColour(), slotNumbers);

			// update slotNumberByRegistrationNumber with unparked vehicle details
			this.slotNumberByRegistrationNumber.remove(vehicle.getRegistrationNumber());

		} else {
			throw new ParkingException(ExceptionMessages.PARKING_NOT_EXIST_ERROR.getMessage());
		}
	}

	@Override
	public Map<Slot, Vehicle> getStatus() throws ParkingException {
		return this.parkingLotMap;
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

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

}
