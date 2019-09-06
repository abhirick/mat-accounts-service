/**
 *
 */
package com.abhirick.matrimonial.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CreateProfileForEnum {

	SELF("Self"), 
	SON("Son"), 
	DAUGHTER("daughter"), 
	SISTER("Sister"),
	BROTHER("Brother"), 
	FRIEND_OR_RELATIVE("Friend or Relative"),
	CLIENT_MARRIAGE_BUREAU("Cleint Marraige Bureau");

	private String value;

	CreateProfileForEnum(String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static CreateProfileForEnum fromValue(String text) {
		for (CreateProfileForEnum b : CreateProfileForEnum.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

}
