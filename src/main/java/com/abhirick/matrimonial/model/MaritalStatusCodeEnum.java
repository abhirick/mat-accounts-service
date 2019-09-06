/**
 *
 */
package com.abhirick.matrimonial.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MaritalStatusCodeEnum {

	NEVER_MARRIED("Never Married"), 
	MARRIED("Married"), 
	AWAITING_DIVORCE("Awaiting Divorce"), 
	DIVORCED("Divorced"),
	WIDOWED("Widowed"), 
	ANNULED("Annuled");

	private String value;

	MaritalStatusCodeEnum(String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static MaritalStatusCodeEnum fromValue(String text) {
		for (MaritalStatusCodeEnum b : MaritalStatusCodeEnum.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

}
