package io.gojek.parkinglot;

import io.gojek.parkinglot.executor.RequestExecutor;
import io.gojek.parkinglot.service.impl.ParkingServiceImpl;

/**
 * @author Mandeep Singh
 *
 */
public class App {
	public static void main(String[] args) {
		RequestExecutor executor = new RequestExecutor();
		executor.setParkingService(new ParkingServiceImpl());
		executor.getInput(args);
	}
}
