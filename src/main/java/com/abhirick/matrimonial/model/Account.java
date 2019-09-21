package com.abhirick.matrimonial.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

	@Field(value = "user_name")
	private String username;

	private String password;

	private int active;

	private String roles = "";

	private String permissions = "";

	public Account(String username, String password, String roles, String permissions) {
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.permissions = permissions;
		this.active = 1;
	}

	public List<String> getRoleList() {
		if (this.roles.length() > 0) {
			return Arrays.asList(this.roles.split(","));
		}
		return new ArrayList<>();
	}

	public List<String> getPermissionList() {
		if (this.permissions.length() > 0) {
			return Arrays.asList(this.permissions.split(","));
		}
		return new ArrayList<>();
	}

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
