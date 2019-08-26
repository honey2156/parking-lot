package io.gojek.parkinglot.model;

import io.gojek.parkinglot.exception.ParkingException;

/**
 * @author Mandeep Singh
 *
 */
public class Command {

	String commandName;
	String params[];

	public String getCommandName() {
		return commandName;
	}

	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

	public static Command validate(String input) throws ParkingException {
		String commandArgs[] = input.split(" ");
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
		return null;
	}
}
