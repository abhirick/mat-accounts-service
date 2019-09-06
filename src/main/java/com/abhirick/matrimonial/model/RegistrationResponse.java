package com.abhirick.matrimonial.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class RegistrationResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("Registration")
	private Registration registration;

	@Data
	@NoArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Registration implements Serializable {

		private static final long serialVersionUID = 1L;

		@JsonProperty("EmailId")
		private String emailId;

		@JsonProperty("Status")
		private String status;

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

			@JsonProperty("AccountID")
			private String accountId;

		}
	}

}
