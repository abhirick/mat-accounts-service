package com.abhirick.matrimonial.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.validation.annotation.Validated;

import com.abhirick.matrimonial.validator.PasswordMatches;
import com.abhirick.matrimonial.validator.ValidEmail;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Validated
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@PasswordMatches
public class RegistrationRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("Registration")
	private Registration registration;

	@Data
	@NoArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Registration implements Serializable {

		private static final long serialVersionUID = 1L;

		@ValidEmail
		@JsonProperty("EmailId")
		private String emailId;

		@JsonProperty("Password")
		private String password;

		@JsonProperty("MatchingPassword")
		private String matchingPassword;

		@JsonProperty("Data")
		private AccountData data;

		@Data
		@NoArgsConstructor
		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(Include.NON_NULL)
		public static class AccountData implements Serializable {

			private static final long serialVersionUID = 1L;

			@JsonProperty("FirstName")
			private String firstName;

			@JsonProperty("LastName")
			private String lastName;

			@JsonProperty("DOB")
			private Date dob;

			@JsonProperty("Gender")
			private String gender;

			@JsonProperty("MaritalStatus")
			private String maritalStatus;

			@JsonProperty("CreateProfileFor")
			private String createProfileFor;

			@JsonProperty("MotherTongue")
			private String motherTongue;

			@JsonProperty("Religion")
			private String religion;

			@JsonProperty("City")
			private String city;

			@JsonProperty("MobileNumber")
			private String mobileNumber;

			@JsonProperty("HeightInCM")
			private long heightInCM;

		}
	}

}
