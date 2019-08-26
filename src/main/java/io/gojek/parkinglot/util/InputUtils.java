package io.gojek.parkinglot.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.invoke.ConstantCallSite;
import java.util.Map;

import io.gojek.parkinglot.constants.CommandsParameterMap;
import io.gojek.parkinglot.constants.ExceptionMessages;
import io.gojek.parkinglot.exception.ParkingException;

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

	public void closeReader() throws IOException {
		if (reader != null) {
			reader.close();
		}
	}

	public static boolean validateInput(String input) throws ParkingException {
		System.out.println("validating " + input);
		boolean valid = true;

//		String commandArgs[] = input.split(" ");
//
//		Map<String, Integer> cmdMap = CommandsParameterMap.getCommandsParameterMap();
//		
//		if(cmdMap.containsKey(commandArgs[0])) {
//			String key = commandArgs[0];
//			int reqParamsCount = cmdMap.get(key);
//			
//			if(reqParamsCount == 1) {
//				
//			}
//			
//		}else {
//			throw new ParkingException(ExceptionMessages.INVALID_COMMAND.getMessage());
//		}

		return valid;
	}
	
//	public
}
