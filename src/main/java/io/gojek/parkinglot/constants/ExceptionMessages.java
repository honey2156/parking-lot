package io.gojek.parkinglot.constants;

/**
 * @author Mandeep Singh
 *
 */
public enum ExceptionMessages {

	PARKING_LOT_FULL("Sorry, parking lot is full"),
	PARKING_LOT_DOES_NOT_EXIST("Sorry, parking lot Does not exist"),
	NOT_FOUND("Not found"),
	PARKING_ALREADY_EXIST("Sorry Parking Already Created, It CAN NOT be again recreated."),
	PARKING_NOT_EXIST_ERROR("Sorry, Car Parking Does not Exist"),
	INVALID_VALUE("value is incorrect"),
	INVALID_FILE("Invalid File"),
	PROCESSING_ERROR("Processing Error "),
	INVALID_REQUEST("Invalid Request"),
	INVALID_COMMAND("Invalid Command"),
	VEHICLE_ALREADY_EXIST("Sorry, vehicle is already parked."),
	INVALID_ARGUMENTS("Invalid arguments");

	private String message;

	private ExceptionMessages(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
