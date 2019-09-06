/**
 *
 */
package com.abhirick.matrimonial.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MotherTongueEnum {

	ENGLISH("English"), 
	HINDI("Hindi"), 
	BHOJPURI("Bhojpuri"), 
	BENGALI("Bengali"), 
	MARATHI("Marathi"),
	Kannada("Kananada"), 
	ORRIYA("Orriya"), 
	PUNJABI("Punjabi"), 
	TELEGU("Telegu"), 
	SINDHI("Sindhi"), 
	GUJRATI("Gujrati"),
	URDU("Urdu"), 
	MALYALAM("Malyalam"), 
	TULU("Tulu"), 
	MARWARI("Marwari");

	private String value;

	MotherTongueEnum(String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static MotherTongueEnum fromValue(String text) {
		for (MotherTongueEnum b : MotherTongueEnum.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

}
