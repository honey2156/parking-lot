package io.gojek.parkinglot.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mandeep Singh
 *
 */
public class CommandsParameterMap {

	private static Map<String, Integer> commandsParameterMap = new HashMap<>();

	static {
		commandsParameterMap.put(Commands.CREATE_PARKING_LOT, 1);
		commandsParameterMap.put(Commands.PARK, 2);
		commandsParameterMap.put(Commands.LEAVE, 1);
		commandsParameterMap.put(Commands.STATUS, 0);
		commandsParameterMap.put(Commands.REG_NUMBER_FOR_CARS_WITH_COLOR, 1);
		commandsParameterMap.put(Commands.SLOTS_NUMBER_FOR_REG_NUMBER, 1);
		commandsParameterMap.put(Commands.SLOTS_NUMBER_FOR_CARS_WITH_COLOR, 1);
	}

	public static Map<String, Integer> getCommandsParameterMap() {
		return commandsParameterMap;
	}

	/**
	 * Method for adding more querying commands for future
	 * 
	 * @param command
	 * @param parameterCount
	 */
	public static void addCommand(String command, int parameterCount) {
		commandsParameterMap.put(command, parameterCount);
	}

}
