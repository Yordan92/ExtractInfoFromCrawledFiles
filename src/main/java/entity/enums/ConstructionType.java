package entity.enums;

import entity.enums.interfaces.Properties;

public enum ConstructionType implements Properties {
	BRICK("Тухла"),
	BEAMS("Гредоред"),
	PANEL("Панел"),
	EPK("ЕПК");
	
	private String bgTranslate;
	
	public String getBgTranslate() {
		return this.bgTranslate;
	}
	
	private ConstructionType(String bgTranslate) {
		this.bgTranslate = bgTranslate;
	}
}
