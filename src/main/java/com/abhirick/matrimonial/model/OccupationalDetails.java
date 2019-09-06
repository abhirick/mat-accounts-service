package com.abhirick.matrimonial.model;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OccupationalDetails {

	@Field(value = "employed_in")
	private String employedIn;

	private String occupation;

	@Field(value = "organization_name")
	private String organizationName;

	@Field(value = "annual_income")
	private String annualIncome;

}
