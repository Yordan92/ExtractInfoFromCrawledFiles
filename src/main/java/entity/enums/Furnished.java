
package entity.enums;

import entity.enums.interfaces.Properties;

public enum Furnished implements Properties {
	SEMI_FURNISHED("Полуобзаведен"),
	NOT_FURNISHED("Необзаведен"),
	FURNISHED("Обзаведен");
	
	private String bgTranslate;

	public String getBgTranslate() {
		return bgTranslate;
	}
	
	private Furnished(String bgTranslate) {
		this.bgTranslate = bgTranslate;
	}
}
