package io.gojek.parkinglot.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.gojek.parkinglot.constants.Commands;
import io.gojek.parkinglot.constants.CommandsParameterMap;
import io.gojek.parkinglot.constants.ExceptionMessages;
import io.gojek.parkinglot.exception.ParkingException;
import io.gojek.parkinglot.model.Command;
import io.gojek.parkinglot.model.Vehicle;

/**
 * @author Mandeep Singh
 *
 */
public class InputUtils {

	public static BufferedReader reader = null;

	public static void initFileReader(String fileName) throws FileNotFoundException {
		reader = new BufferedReader(new FileReader(new File(fileName)));
	}

	public static void initConsoleReader() {
		reader = new BufferedReader(new InputStreamReader(System.in));
	}

	public static void closeReader() throws IOException {
		if (reader != null) {
			reader.close();
		}
	}

	public static boolean validateInput(String input) throws ParkingException {
		boolean valid = false;
		if (Command.parseInput(input) != null) {
			valid = true;
		}
		return valid;
	}

	public static int isNumber(String input) {
		int number = -1;
		try {
			number = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			throw e;
		}
		return number;
	}

}
