package io.gojek.parkinglot.model;

/**
 * @author Mandeep Singh
 *
 */
public class Slot {

	private int slotNumber;

	public Slot(int slotNumber) {
		super();
		this.slotNumber = slotNumber;
	}

	public int getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(int slotNumber) {
		this.slotNumber = slotNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + slotNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Slot other = (Slot) obj;
		if (slotNumber != other.slotNumber)
			return false;
		return true;
	}

}
