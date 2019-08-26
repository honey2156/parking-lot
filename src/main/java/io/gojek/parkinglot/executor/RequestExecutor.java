package io.gojek.parkinglot.executor;

import io.gojek.parkinglot.service.ParkingService;

/**
 * @author Mandeep Singh
 *
 */
public class RequestExecutor {

	private ParkingService parkingService;

	public void setParkingService(ParkingService parkingService) {
		this.parkingService = parkingService;
	}
	
	public void execute() {
		System.out.println("executing");
	}
	
}
