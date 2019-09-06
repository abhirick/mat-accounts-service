package com.abhirick.matrimonial.model;

import java.io.Serializable;

import org.springframework.validation.annotation.Validated;

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
public class UpdateContactDetailsRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("Data")
	private ContactDetailsRequest data;

	@Data
	@NoArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class ContactDetailsRequest implements Serializable {

		private static final long serialVersionUID = 1L;

		@JsonProperty(value = "AlternateEmailId")
		private String alternateEmailId;

		@JsonProperty(value = "MobileNumber")
		private String mobileNumber;

		@JsonProperty(value = "AlternateMobileNumber")
		private String alternateMobileNumber;

		@JsonProperty(value = "LandlineNumber")
		private String landlineNumber;

		@JsonProperty(value = "SuitableTimeToCall")
		private String suitableTimeToCall;

		@JsonProperty(value = "ContactAddress")
		private String contactAddress;

		@JsonProperty(value = "ParentsAddress")
		private String parentsAddress;

		@JsonProperty(value = "City")
		private String city;

		@JsonProperty(value = "Country")
		private String country;
	}

}
