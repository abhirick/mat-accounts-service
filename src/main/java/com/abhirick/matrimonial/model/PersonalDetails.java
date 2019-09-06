package com.abhirick.matrimonial.model;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonalDetails {

	@Field(value = "about_me")
	private String aboutMe;

	@Field(value = "about_my_family")
	private String aboutMyFamily;

	private String education;

	private String occupation;

}
