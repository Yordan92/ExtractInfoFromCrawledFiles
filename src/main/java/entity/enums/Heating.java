package entity.enums;

import entity.enums.interfaces.Properties;

public enum Heating implements Properties {
	TEC("ТЕЦ"),
	LOCAL_HEATING("Локално отопление"),
	ELECTRIC_HEATING("Електричество"),
	NO_HEATING("Без отопление");
	
	private String bgTranslate;
	
	public String getBgTranslate() {
		return this.bgTranslate;
	}
	
	private Heating(String bgTranslate) {
		this.bgTranslate = bgTranslate;
	}
}
