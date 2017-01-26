package entity.entities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Floors {
	@Column(name="FLOOR")
	private int floor;
	@Column(name="MAX_NUMBERTS_OF_FLOORS")
	private int maxNumbersOfFloors;
	
	private static final Pattern pattern = Pattern.compile("Етаж\\s(\\d{1,3})(\\sот\\s)?(\\d{0,3})");
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public int getMaxNumbersOfFloors() {
		return maxNumbersOfFloors;
	}
	public void setMaxNumbersOfFloors(int maxNumbersOfFloors) {
		this.maxNumbersOfFloors = maxNumbersOfFloors;
	}
	
	public static Floors setByRegex(String text) {
		Matcher m = pattern.matcher(text);
		Floors f = new Floors();
		if (m.find()) {
			f.setFloor(Integer.parseInt(m.group(1)));
			f.setMaxNumbersOfFloors(m.group(3).isEmpty() ? -1 : Integer.parseInt(m.group(3))); 
		}
		return f;
	}
	@Override
	public String toString() {
		return "Floors [floor=" + floor + ", maxNumbersOfFloors=" + maxNumbersOfFloors + "]";
	}
	

}
