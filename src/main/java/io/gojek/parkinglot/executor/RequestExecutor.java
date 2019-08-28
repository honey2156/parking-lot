package io.gojek.parkinglot.executor;

import java.io.FileNotFoundException;
import java.io.IOException;

import io.gojek.parkinglot.constants.Commands;
import io.gojek.parkinglot.constants.ExceptionMessages;
import io.gojek.parkinglot.exception.ParkingException;
import io.gojek.parkinglot.model.Car;
import io.gojek.parkinglot.model.Command;
import io.gojek.parkinglot.model.Vehicle;
import io.gojek.parkinglot.service.ParkingService;
import io.gojek.parkinglot.util.InputUtils;

/**
 * @author Mandeep Singh
 *
 */
public class RequestExecutor {

	/**
	 * Reference to Parking service
	 */
	private ParkingService parkingService;

	/**
	 * @param parkingService
	 */
	public void setParkingService(ParkingService parkingService) {
		this.parkingService = parkingService;
	}

	public void execute(String input) throws ParkingException {
		Command command = Command.parseInput(input);

		switch (command.getCommandName()) {
		case Commands.CREATE_PARKING_LOT: {
			int size = Integer.parseInt(command.getParams().get(0));
			parkingService.createParkingLot(size);
			break;
		}
		case Commands.PARK: {
			String regNumber = command.getParams().get(0);
			String colour = command.getParams().get(1);
			Vehicle vehicle = new Car(regNumber, colour);
			parkingService.park(vehicle);
			break;
		}
		case Commands.LEAVE: {
			int slotNumber = Integer.parseInt(command.getParams().get(0));
			parkingService.unPark(slotNumber);
			break;
		}
		case Commands.STATUS: {
			parkingService.getStatus();
			break;
		}
		case Commands.REG_NUMBER_FOR_CARS_WITH_COLOR: {
			String colour = command.getParams().get(0);
			parkingService.getRegistrationNumbersOfColour(colour);
			break;
		}
		case Commands.SLOTS_NUMBER_FOR_CARS_WITH_COLOR: {
			String colour = command.getParams().get(0);
			parkingService.getSlotNumbersOfColour(colour);
			break;
		}
		case Commands.SLOTS_NUMBER_FOR_REG_NUMBER: {
			String regNumber = command.getParams().get(0);
			parkingService.getSlotNumberFromRegistrationNumber(regNumber);
			break;
		}
		default: {
			break;
		}
		}
	}

	public void getInputAndExecute(String[] args) {
		String input;

		switch (args.length) {

		// Console Input
		case 0: {
			InputUtils.initConsoleReader();

			try {
				while (true) {
					input = InputUtils.reader.readLine().trim();

					if ("exit".equalsIgnoreCase(input)) {
						break;
					} else if (InputUtils.validateInput(input)) {
						execute(input);
					} else {
						throw new ParkingException(ExceptionMessages.INVALID_REQUEST.getMessage());
					}
				}
			} catch (ParkingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					InputUtils.closeReader();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		}
		case 1: { // File Input

			try {
				InputUtils.initFileReader(args[0]);

				while ((input = InputUtils.reader.readLine()) != null) {
					input = input.trim();
					if ("exit".equalsIgnoreCase(input)) {
						break;
					} else if (InputUtils.validateInput(input)) {
						execute(input);
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParkingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					InputUtils.closeReader();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			break;
		}
		}
	}

}
