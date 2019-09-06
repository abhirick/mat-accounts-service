/**
 *
 */
package com.abhirick.matrimonial.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum GenderCodeEnum {

	MALE("Male"), FEMALE("Female");

	private String value;

	GenderCodeEnum(String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static GenderCodeEnum fromValue(String text) {
		for (GenderCodeEnum b : GenderCodeEnum.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

}
