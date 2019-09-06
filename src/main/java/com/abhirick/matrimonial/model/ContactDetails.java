package com.abhirick.matrimonial.model;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContactDetails {

	@Field(value = "email_Id")
	private String emailId;

	@Field(value = "alternate_email_id")
	private String alternateEmailId;

	@Field(value = "mobile_number")
	private String mobileNumber;

	@Field(value = "alternate_mobile_number")
	private String alternateMobileNumber;

	@Field(value = "landline_number")
	private String landlineNumber;

	@Field(value = "suitable_time_to_call")
	private String suitableTimeToCall;

	@Field(value = "contact_address")
	private String contactAddress;

	@Field(value = "parents_address")
	private String parentsAddress;

	private String city;

	private String country;

}
