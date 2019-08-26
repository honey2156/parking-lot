package io.gojek.parkinglot.executor;

import java.io.FileNotFoundException;
import java.io.IOException;

import io.gojek.parkinglot.constants.ExceptionMessages;
import io.gojek.parkinglot.exception.ParkingException;
import io.gojek.parkinglot.service.ParkingService;
import io.gojek.parkinglot.util.InputUtils;

/**
 * @author Mandeep Singh
 *
 */
public class RequestExecutor {

	private ParkingService parkingService;

	public void setParkingService(ParkingService parkingService) {
		this.parkingService = parkingService;
	}

	public void getInput(String[] args) {
		System.out.println("executing");
		String input;
		switch (args.length) {

		// Console Input
		case 0: {
			InputUtils.initConsoleReader();

			while (true) {
				try {
					input = InputUtils.reader.readLine().trim();

					if ("exit".equalsIgnoreCase(input)) {
						break;
					} else if (InputUtils.validateInput(input)) {
						execute(input);
					} else {
						throw new ParkingException(ExceptionMessages.INVALID_REQUEST.getMessage());
					}
				} catch (IOException | ParkingException e) {
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

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParkingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}

	}

	public void execute(String input) throws ParkingException {

	}
}
