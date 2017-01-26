package entity.enums;

import entity.enums.interfaces.Properties;

public enum Extras implements Properties{
	COOKER("Готварска печка"),
	REFRIGERATOR("Хладилник"),
	WASHING_MACHINE("Перална машина"),
	DISHWASHER("Миялна машина"),
	AC("Климатик"),
	BATH_JACUZZI("Вана/Джакузи"),
	ELEVATOR("Асансьор"),
	GARAGE("Гараж"),
	PARKING("Паркомясто"),
	SOT("СОТ"),
	PORTRT_SECURITY("Портиер/Охрана"),
	TV("Телевизор"),
	LUXURY_PROPERTY("Луксозен имот"),
	INTERNET("Интернет"),
	GATED_COMPLEX("Затворен комплекс"),
	RENOVATED("Саниран");
	
	
	private final String bgTranslate;
	
	public String getBgTranslate() {
		return bgTranslate;
	}

	private Extras(String bgTranslate) {
		this.bgTranslate = bgTranslate;
	}
}
