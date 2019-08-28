package io.gojek.parkinglot.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.gojek.parkinglot.constants.Commands;
import io.gojek.parkinglot.constants.CommandsParameterMap;
import io.gojek.parkinglot.constants.ExceptionMessages;
import io.gojek.parkinglot.exception.ParkingException;
import io.gojek.parkinglot.util.InputUtils;

/**
 * @author Mandeep Singh
 *
 */
public class Command {

	String commandName;
	List<String> params;

	public Command() {
		super();
	}

	public String getCommandName() {
		return commandName;
	}

	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}

	public List<String> getParams() {
		return params;
	}

	public void setParams(List<String> params) {
		this.params = params;
	}

	public static Command parseInput(String input) throws ParkingException {
		String commandArgs[] = input.split(" ");

		Map<String, Integer> cmdMap = CommandsParameterMap.getCommandsParameterMap();

		Command command = null;

		if (cmdMap.containsKey(commandArgs[0])) {
			String commandName = commandArgs[0];
			int reqParamsCount = cmdMap.get(commandName);
			if (reqParamsCount == commandArgs.length - 1) {

				switch (commandName) {
				case Commands.CREATE_PARKING_LOT: {
					int size = InputUtils.isNumber(commandArgs[1]);
					if (size < 0) {
						throw new NumberFormatException(ExceptionMessages.INVALID_VALUE.getMessage());
					}
					command = new Command();
					command.setCommandName(commandName);
					List<String> params = new ArrayList<String>();
					params.add(commandArgs[1]);
					command.setParams(params);
					break;
				}
				case Commands.PARK: {
					if (!Vehicle.isRegistrationNumber(commandArgs[1]) || !Vehicle.isColour(commandArgs[2])) {
						throw new ParkingException(ExceptionMessages.INVALID_ARGUMENTS.getMessage());
					}
					command = new Command();
					command.setCommandName(commandName);
					List<String> params = new ArrayList<String>();
					params.add(commandArgs[1]);
					params.add(commandArgs[2]);
					command.setParams(params);
					break;
				}
				case Commands.LEAVE: {
					if (!Vehicle.isRegistrationNumber(commandArgs[1]) || !Vehicle.isColour(commandArgs[2])) {
						throw new ParkingException(ExceptionMessages.INVALID_ARGUMENTS.getMessage());
					}
					command = new Command();
					command.setCommandName(commandName);
					List<String> params = new ArrayList<String>();
					params.add(commandArgs[1]);
					params.add(commandArgs[2]);
					command.setParams(params);
					break;
				}
				case Commands.STATUS: {
					command = new Command();
					command.setCommandName(commandName);
					command.setParams(new ArrayList<String>());
					break;
				}
				case Commands.REG_NUMBER_FOR_CARS_WITH_COLOR: {
					if (!Vehicle.isColour(commandArgs[2])) {
						throw new ParkingException(ExceptionMessages.INVALID_ARGUMENTS.getMessage());
					}
					command = new Command();
					command.setCommandName(commandName);
					List<String> params = new ArrayList<String>();
					params.add(commandArgs[1]);
					command.setParams(params);
					break;
				}
				case Commands.SLOTS_NUMBER_FOR_CARS_WITH_COLOR: {
					if (!Vehicle.isColour(commandArgs[2])) {
						throw new ParkingException(ExceptionMessages.INVALID_ARGUMENTS.getMessage());
					}
					command = new Command();
					command.setCommandName(commandName);
					List<String> params = new ArrayList<String>();
					params.add(commandArgs[1]);
					command.setParams(params);
					break;
				}
				case Commands.SLOTS_NUMBER_FOR_REG_NUMBER: {
					command = new Command();
					if (!Vehicle.isRegistrationNumber(commandArgs[1])) {
						throw new ParkingException(ExceptionMessages.INVALID_ARGUMENTS.getMessage());
					}
					command.setCommandName(commandName);
					List<String> params = new ArrayList<String>();
					params.add(commandArgs[1]);
					command.setParams(params);
					break;
				}
				}
			} else {
				throw new ParkingException(ExceptionMessages.INVALID_ARGUMENTS.getMessage());
			}

		} else {
			throw new ParkingException(ExceptionMessages.INVALID_COMMAND.getMessage());
		}
		return command;
	}
}
