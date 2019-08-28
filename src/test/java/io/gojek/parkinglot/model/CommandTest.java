package io.gojek.parkinglot.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import io.gojek.parkinglot.constants.Commands;
import io.gojek.parkinglot.exception.ParkingException;

public class CommandTest {

	@Test
	public void testParseInput() {
		Command command = new Command();
		List<String> params = new ArrayList<String>();
		params.add("6");
		command.setCommandName(Commands.CREATE_PARKING_LOT);
		command.setParams(params);

		try {
			assertEquals(command, Command.parseInput("create_parking_lot 6"));
			assertNotEquals(command, Command.parseInput("create_parking_lot 7"));
		} catch (ParkingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
