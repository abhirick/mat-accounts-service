package com.abhirick.matrimonial.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "account")
@Data
@NoArgsConstructor
public class Account {

	@Id
	private String id;

	@Field(value = "first_name")
	private String firstName;

	@Field(value = "last_name")
	private String lastName;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Field(value = "date_of_birth")
	private Date dob;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Field(value = "last_updated_at")
	private Date lastUpdatedAt;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Field(value = "created_at")
	private Date createdAt;

	private GenderCodeEnum gender;

	@Field(value = "marital_status")
	private MaritalStatusCodeEnum maritalStatus;

	private int age;

	@Field(value = "create_profile_for")
	private CreateProfileForEnum createProfileFor;

	@Field(value = "mother_tongue")
	private MotherTongueEnum motherTongue;

	private ReligionEnum religion;

	private long height;

	@Field(value = "contact_details")
	private ContactDetails contactDetails;

	@Field(value = "education_details")
	private EducationDetails educationDetails;

	@Field(value = "personal_details")
	private PersonalDetails personalDetails;

	@Field(value = "occupational_details")
	private OccupationalDetails occupationalDetails;

	@Field(value = "family_details")
	private FamilyDetails familyDetails;

}
