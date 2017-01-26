package entity.enums;

import entity.enums.interfaces.Properties;

public enum BuildYear implements Properties{
	AFTER_2016("след 2006г."),
	BETWEEN_2000_2005("2000 и 2005г."),
	BETWEEN_1990_1999("1990 и 1999г."),
	BEFORE_1990("преди 1990г.");
	
	private String bgTranslate;

	public String getBgTranslate() {
		return bgTranslate;
	}

	private BuildYear(String year) {
		this.bgTranslate = year;
	} 
	
}
