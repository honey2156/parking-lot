package io.gojek.parkinglot.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

	public static boolean validateInput(String input) {
		System.out.println("validating");
		return false;
	}
}
