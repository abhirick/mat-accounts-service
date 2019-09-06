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
public class GetAccountDetailsResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("CriticalFields")
	private CriticalFields criticalFields;

	@JsonProperty("BasicDetails")
	private BasicDetails basicDetails;

	@JsonProperty("AboutMe")
	private AboutMe aboutMe;

	@JsonProperty("EducationDetails")
	private EducationDetails educationDetails;

	@JsonProperty("CarrierDetails")
	private CarrierDetails carrierDetails;

	@JsonProperty("FamilyDetails")
	private FamilyDetails familyDetails;

	@JsonProperty("ContactDetails")
	private ContactDetail contactDetails;

	@Data
	@NoArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class CriticalFields implements Serializable {

		private static final long serialVersionUID = 1L;

		@JsonProperty("Age")
		private String age;

		@JsonProperty("MaritalStatus")
		private String maritalStatus;

		@JsonProperty("AccountID")
		private String accountId;

	}

	@Data
	@NoArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class BasicDetails implements Serializable {

		private static final long serialVersionUID = 1L;

		@JsonProperty("FirstName")
		private String firstName;

		@JsonProperty("LastName")
		private String lastName;

		@JsonProperty(value = "CreateProfileFor")
		private String createProfileFor;

		@JsonProperty(value = "MotherTongue")
		private String motherTongue;

		@JsonProperty(value = "Religion")
		private String religion;

		@JsonProperty(value = "Height")
		private long height;

		@JsonProperty(value = "City")
		private String city;

	}

	@Data
	@NoArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class AboutMe implements Serializable {

		private static final long serialVersionUID = 1L;

		@JsonProperty(value = "AboutMe")
		private String aboutMe;

		@JsonProperty(value = "AboutMyFamily")
		private String aboutMyFamily;

		@JsonProperty(value = "Education")
		private String education;

		@JsonProperty(value = "Occupation")
		private String occupation;

	}

	@Data
	@NoArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class EducationDetails implements Serializable {

		private static final long serialVersionUID = 1L;

		@JsonProperty(value = "HighestEducation")
		private String highestEducation;

		@JsonProperty(value = "SchoolName")
		private String schoolName;

		@JsonProperty(value = "UGDegree")
		private String ugDegree;

		@JsonProperty(value = "UGCollege")
		private String ugCollege;

		@JsonProperty(value = "PGDegree")
		private String pgDegree;

		@JsonProperty(value = "PGCollege")
		private String pgCollege;

	}

	@Data
	@NoArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class CarrierDetails implements Serializable {

		private static final long serialVersionUID = 1L;

		@JsonProperty(value = "EmployedIn")
		private String employedIn;

		@JsonProperty(value = "Occupation")
		private String occupation;

		@JsonProperty(value = "OrganizationName")
		private String organizationName;

		@JsonProperty(value = "AnnualIncome")
		private String annualIncome;

	}

	@Data
	@NoArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class FamilyDetails implements Serializable {

		private static final long serialVersionUID = 1L;

		@JsonProperty(value = "MothersOccupation")
		private String mothersOccupation;

		@JsonProperty(value = "FathersOccupation")
		private String fathersOccupation;

		@JsonProperty(value = "NoOfSisters")
		private int noOfSisters;

		@JsonProperty(value = "NoOfBrothers")
		private String noOfBrothers;

		@JsonProperty(value = "Gothra")
		private String gothra;

		@JsonProperty(value = "FamilyStatus")
		private String familyStatus;

		@JsonProperty(value = "FamilyIncome")
		private String familyIncome;

		@JsonProperty(value = "FamilyValues")
		private String familyValues;

		@JsonProperty(value = "FamilyBasedOutOf")
		private String familyBasedOutOf;

		@JsonProperty(value = "City")
		private String city;

		@JsonProperty(value = "LivingWithParents")
		private boolean livingWithParents;

	}

	@Data
	@NoArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class ContactDetail implements Serializable {

		private static final long serialVersionUID = 1L;

		@JsonProperty(value = "EmailId")
		private String emailId;

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
