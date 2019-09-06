package com.abhirick.matrimonial.model;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EducationDetails {

	@Field(value = "highest_education")
	private String highestEducation;

	@Field(value = "school_name")
	private String schoolName;

	@Field(value = "ug_degree")
	private String ugDegree;

	@Field(value = "ug_college")
	private String ugCollege;

	@Field(value = "pg_degree")
	private String pgDegree;

	@Field(value = "pg_college")
	private String pgCollege;

}
