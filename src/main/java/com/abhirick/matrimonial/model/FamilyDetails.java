package com.abhirick.matrimonial.model;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FamilyDetails {

	@Field(value = "mothers_occupation")
	private String mothersOccupation;

	@Field(value = "fathers_occupation")
	private String fathersOccupation;

	@Field(value = "no_of_sisters")
	private int noOfSisters;

	@Field(value = "no_of_brothers")
	private String noOfBrothers;

	private String gothra;

	@Field(value = "family_status")
	private String familyStatus;

	@Field(value = "family_income")
	private String familyIncome;

	@Field(value = "family_values")
	private String familyValues;

	@Field(value = "family_based_out_of")
	private String familyBasedOutOf;

	private String city;

	@Field(value = "living_with_parents")
	private boolean livingWithParents;

}
