package io.gojek.parkinglot.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.gojek.parkinglot.constants.ExceptionCodes;
import io.gojek.parkinglot.constants.ExceptionMessages;
import io.gojek.parkinglot.exception.ParkingException;
import io.gojek.parkinglot.model.Slot;
import io.gojek.parkinglot.model.Vehicle;

/**
 * @author Mandeep Singh
 *
 */
public class ParkingDaoImpl implements ParkingDao {

	/**
	 * Persists Parking lot size
	 */
	private int parkingLotSize;

	/**
	 * Persists Parking lot Slot-Vehicle Mapping
	 */
	private Map<Slot, Vehicle> parkingLotMap;

	/**
	 * Persists mapping of registration numbers of vehicles by colour of vehicles
	 */
	private Map<String, List<String>> registrationNumbersByColour;

	/**
	 * Persists mapping of slot numbers of parking lot by colour of vehicles
	 */
	private Map<String, List<Integer>> slotNumbersByColour;

	/**
	 * Perists mapping of slot numbers of parking lot by registration number of
	 * vehicles
	 */
	private Map<String, Integer> slotNumberByRegistrationNumber;

	/**
	 * Maintains record of empty slots for parking in sorted order for allocating
	 * nearest slot from entry point
	 */
	private List<Integer> emptySlots;

	/**
	 * For implementing Singleton instance of Parking DAO (Assuming there will be
	 * only one parking lot)
	 */
	private static ParkingDaoImpl instance = null;

	/**
	 * @param parkingLotSize
	 */
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

	/**
	 * @param parkingLotSize
	 * @param parkingLotMap
	 * @param registrationNumbersByColour
	 * @param slotNumbersByColour
	 * @param slotNumberByRegistrationNumber
	 * @param emptySlots
	 */
	private ParkingDaoImpl(int parkingLotSize, Map<Slot, Vehicle> parkingLotMap,
			Map<String, List<String>> registrationNumbersByColour, Map<String, List<Integer>> slotNumbersByColour,
			Map<String, Integer> slotNumberByRegistrationNumber, List<Integer> emptySlots) {
		super();
		this.parkingLotSize = parkingLotSize;
		this.parkingLotMap = parkingLotMap;
		this.registrationNumbersByColour = registrationNumbersByColour;
		this.slotNumbersByColour = slotNumbersByColour;
		this.slotNumberByRegistrationNumber = slotNumberByRegistrationNumber;
		this.emptySlots = emptySlots;
	}

	/**
	 * For providing external access to Parking DAO
	 * 
	 * @param size
	 * @return
	 */
	public static ParkingDao getInstance(int size) {
		if (instance == null) {
			instance = new ParkingDaoImpl(size);
		}
		return instance;
	}

	/**
	 * Park vehicle by allocating it slot nearest to entry point
	 * 
	 * @param vehicle
	 * @return
	 */
	@Override
	public int park(Vehicle vehicle) throws ParkingException {
		validateParkingLot();
		// Check slot availability
		if (this.getAvailableSlotsCount() == 0) {
			// return -1 if slot not available
			return ExceptionCodes.NOT_AVAILABLE;
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
		List<Integer> slotNumbers;
		if (this.slotNumbersByColour.containsKey(vehicle.getColour())) {
			slotNumbers = this.slotNumbersByColour.get(vehicle.getColour());
		} else {
			slotNumbers = new ArrayList<>();
		}
		slotNumbers.add(nearestSlot);
		this.slotNumbersByColour.put(vehicle.getColour(), slotNumbers);

		// update slotNumberByRegistrationNumber
		this.slotNumberByRegistrationNumber.put(vehicle.getRegistrationNumber(), nearestSlot);

		// remove empty slot
		this.emptySlots.remove(0);
		// return allocated slot
		return nearestSlot;
	}

	/**
	 * Unpark vehicle from slot with given slot number
	 * 
	 * @param slotNumber
	 */
	@Override
	public boolean unPark(int slotNumber) throws ParkingException {
		validateParkingLot();
		Slot slot = new Slot(slotNumber);
		if (this.parkingLotMap.containsKey(slot) && this.parkingLotMap.get(slot) != null) {
			// unPark vehicle from slot
			Vehicle vehicle = this.parkingLotMap.get(slot);

			// update registrationNumbersByColour with unparked vehicle details
			List<String> registerationNumbers = this.registrationNumbersByColour.get(vehicle.getColour());
			registerationNumbers.remove(vehicle.getRegistrationNumber());
			this.registrationNumbersByColour.put(vehicle.getColour(), registerationNumbers);

			// update slotNumbersByColour with unparked vehicle details
			List<Integer> slotNumbers = this.slotNumbersByColour.get(vehicle.getColour());
			slotNumbers.remove(slotNumber);
			this.slotNumbersByColour.put(vehicle.getColour(), slotNumbers);

			// update slotNumberByRegistrationNumber with unparked vehicle details
			this.slotNumberByRegistrationNumber.remove(vehicle.getRegistrationNumber());

			return true;
		} else {
			return false;
		}
	}

	/**
	 * Get status of parking lot. Provides layout of parking lot with vehicles
	 * parked in their respective slots
	 */
	@Override
	public Map<Slot, Vehicle> getStatus() throws ParkingException {
		validateParkingLot();
		return this.parkingLotMap;
	}

	/**
	 * Returns registration numbers of vehicles by colour of vehicles
	 *
	 * @param colour
	 */
	@Override
	public List<String> getRegistrationNumbersOfColour(String colour) throws ParkingException {
		validateParkingLot();
		List<String> registrationNumbers = null;
		if (this.registrationNumbersByColour.containsKey(colour)) {
			registrationNumbers = this.registrationNumbersByColour.get(colour);
		}
		return registrationNumbers;
	}

	/**
	 * Returns slot numbers of parking lot allocated with vehicles by colour of
	 * vehicles
	 *
	 * @param colour
	 */
	@Override
	public List<Integer> getSlotNumbersOfColour(String colour) throws ParkingException {
		validateParkingLot();
		List<Integer> slotNumbers = null;
		if (this.slotNumbersByColour.containsKey(colour)) {
			slotNumbers = this.slotNumbersByColour.get(colour);
		}
		return slotNumbers;
	}

	/**
	 * Returns slot numbers of parking lot by registration number of vehicles
	 * 
	 * @param registrationNumber
	 */
	@Override
	public int getSlotNumberFromRegistrationNumber(String registrationNumber) throws ParkingException {
		validateParkingLot();
		int slotNumber = ExceptionCodes.NOT_FOUND;
		if (this.slotNumberByRegistrationNumber.containsKey(registrationNumber)) {
			slotNumber = this.slotNumberByRegistrationNumber.get(registrationNumber);
		}
		return slotNumber;
	}

	/**
	 * Returns available slots for parking
	 * 
	 * @throws ParkingException
	 */
	@Override
	public int getAvailableSlotsCount() throws ParkingException {
		validateParkingLot();
		return this.emptySlots.size();
	}

	/**
	 * Validate parking lot exists or not
	 * 
	 * @throws ParkingException
	 */
	private void validateParkingLot() throws ParkingException {
		if (instance == null) {
			throw new ParkingException(ExceptionMessages.PARKING_LOT_DOES_NOT_EXIST.getMessage());
		}
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		throw new CloneNotSupportedException();
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
