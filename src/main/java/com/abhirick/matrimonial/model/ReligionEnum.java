/**
 *
 */
package com.abhirick.matrimonial.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ReligionEnum {

	HINDU("Hindu"), 
	MUSLIM("Muslium"), 
	SIKH("Sikh"), 
	CHRISTIAN("Christian"),
	BUDDHIST("Buddhist"), 
	PARSI("Parsi"), 
	JAIN("Jain");

	private String value;

	ReligionEnum(String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static ReligionEnum fromValue(String text) {
		for (ReligionEnum b : ReligionEnum.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

}
