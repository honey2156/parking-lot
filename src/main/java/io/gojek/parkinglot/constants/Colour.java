package io.gojek.parkinglot.constants;

/**
 * @author Mandeep Singh
 *
 */
public enum Colour {
	// Add more colours depending on requirement
	VIOLET("violet"), INDIGO("indigo"), BROWN("brown"), GREEN("green"), YELLOW("yellow"), ORANGE("orange"), RED("red"),
	WHITE("white"), PINK("pink"), BLACK("black"), BLUE("blue");

	String value;

	private Colour(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	/**
	 * Checks if a colour input is valid colour (According to current application
	 * colour store)
	 * 
	 * @param colour
	 * @return
	 */
	public static boolean contains(String colour) {
		for (Colour c : Colour.values()) {
			if (c.getValue().equalsIgnoreCase(colour)) {
				return true;
			}
		}
		return false;
	}
}
