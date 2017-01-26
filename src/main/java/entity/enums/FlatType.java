package entity.enums;

import entity.enums.interfaces.Properties;

public enum FlatType implements Properties {
	ONE_ROOM("Едностаен"),
	TWO_ROOMS("Двустаен"),
	THREE_ROOMS("Тристаен"),
	FOUR_ROOMS("Четиристаен"),
	MANY_ROOMS("Многостаен"),
	MAISONETTE("Мезонет"),
	ROOM("Стая"),
	CEILING("Ателие/Таван"),
	GARAGE("Гараж");
	
	private final String bgTranslate;
	
	public String getBgTranslate() {
		return bgTranslate;
	}

	private FlatType(String bgTranslate) {
		this.bgTranslate = bgTranslate;
	}
}
