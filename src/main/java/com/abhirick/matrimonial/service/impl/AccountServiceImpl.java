package com.abhirick.matrimonial.service.impl;

import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.abhirick.matrimonial.builder.GenericBuilder;
import com.abhirick.matrimonial.error.BusinessException;
import com.abhirick.matrimonial.error.ErrorResolver;
import com.abhirick.matrimonial.error.ResourceNotFoundException;
import com.abhirick.matrimonial.model.Account;
import com.abhirick.matrimonial.model.ContactDetails;
import com.abhirick.matrimonial.model.CreateProfileForEnum;
import com.abhirick.matrimonial.model.GenderCodeEnum;
import com.abhirick.matrimonial.model.GetAccountDetailsResponse;
import com.abhirick.matrimonial.model.GetAccountDetailsResponse.AboutMe;
import com.abhirick.matrimonial.model.GetAccountDetailsResponse.BasicDetails;
import com.abhirick.matrimonial.model.GetAccountDetailsResponse.CarrierDetails;
import com.abhirick.matrimonial.model.GetAccountDetailsResponse.ContactDetail;
import com.abhirick.matrimonial.model.GetAccountDetailsResponse.CriticalFields;
import com.abhirick.matrimonial.model.GetAccountDetailsResponse.EducationDetails;
import com.abhirick.matrimonial.model.GetAccountDetailsResponse.FamilyDetails;
import com.abhirick.matrimonial.model.MaritalStatusCodeEnum;
import com.abhirick.matrimonial.model.MotherTongueEnum;
import com.abhirick.matrimonial.model.RegistrationRequest;
import com.abhirick.matrimonial.model.RegistrationResponse;
import com.abhirick.matrimonial.model.RegistrationResponse.Registration;
import com.abhirick.matrimonial.model.RegistrationResponse.Registration.AccountData;
import com.abhirick.matrimonial.model.ReligionEnum;
import com.abhirick.matrimonial.model.UpdateContactDetailsRequest;
import com.abhirick.matrimonial.model.UpdateContactDetailsResponse;
import com.abhirick.matrimonial.model.UpdateContactDetailsResponse.ContactDetailsResponse;
import com.abhirick.matrimonial.repository.AccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AccountServiceImpl implements AccountService {

	private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private ErrorResolver errorResolver;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public RegistrationResponse initiateUserRegistration(final RegistrationRequest registrationRequest) {

		logger.info("Processing in AccountServiceImpl || initiateUserRegistration");
		RegistrationResponse response = null;
		if (Optional.ofNullable(registrationRequest).isPresent()) {

			// Check Repository for the email Id before saving or do it in validation.

			Account accountDBObj = mapAPIRequestToDBObject(registrationRequest);
			accountDBObj = accountRepository.save(accountDBObj);
			if (Optional.ofNullable(accountDBObj).isPresent()) {
				response = mapDBItemToRegistrationResponseObj(accountDBObj);
			} else {
				throw new BusinessException(HttpStatus.BAD_GATEWAY, errorResolver.getErrorMessage("VALIDATION_EXCEPTION"));
			}
		}
		return response;

	}

	private RegistrationResponse mapDBItemToRegistrationResponseObj(final Account accountDBObj) {

		logger.info("Processing in AccountServiceImpl || mapDBItemToRegistrationResponseObj");
		RegistrationResponse response = null;

		if (Optional.ofNullable(accountDBObj).isPresent()) {

			AccountData data = GenericBuilder.of(AccountData::new)
					.with(AccountData::setFirstName, accountDBObj.getFirstName())
					.with(AccountData::setLastName, accountDBObj.getLastName())
					.with(AccountData::setAccountId, accountDBObj.getId())
					.build();

			Registration registration = GenericBuilder.of(Registration::new)
					.with(Registration::setData, data)
					.with(Registration::setEmailId, accountDBObj.getContactDetails().getEmailId())
					.build();

			response = GenericBuilder.of(RegistrationResponse::new)
					.with(RegistrationResponse::setRegistration, registration)
					.build();

			ObjectMapper mapper = new ObjectMapper();
			mapper.writerWithDefaultPrettyPrinter();
			try {
				logger.debug("Registration Response from DB Object ==> {}", mapper.writeValueAsString(response));
			} catch (JsonProcessingException exp) {
				logger.error("Json Processing Exception while printing request ", exp);
			}
		}
		return response;
	}

	private Account mapAPIRequestToDBObject(final RegistrationRequest registrationRequest) {

		logger.info("Processing in AccountServiceImpl || mapAPIRequestToDBObject");
		Account account = null;

		if (Optional.ofNullable(registrationRequest).isPresent()) {

			ContactDetails contactDetails = GenericBuilder.of(ContactDetails::new)
					.with(ContactDetails::setCity, registrationRequest.getRegistration().getData().getCity())
					.with(ContactDetails::setEmailId, registrationRequest.getRegistration().getEmailId())
					.build();

			account = GenericBuilder.of(Account::new)
					.with(Account::setAge, 30)
					.with(Account::setReligion, ReligionEnum.fromValue(registrationRequest.getRegistration().getData().getReligion()))
					.with(Account::setContactDetails, contactDetails)
					.with(Account::setMaritalStatus, MaritalStatusCodeEnum.fromValue(registrationRequest.getRegistration().getData().getMotherTongue()))
					.with(Account::setMotherTongue, MotherTongueEnum.fromValue(registrationRequest.getRegistration().getData().getCreateProfileFor()))
					.with(Account::setCreateProfileFor, CreateProfileForEnum.fromValue(registrationRequest.getRegistration().getData().getCreateProfileFor()))
					.with(Account::setLastUpdatedAt, new Date())
					.with(Account::setCreatedAt, new Date())
					.with(Account::setDob, registrationRequest.getRegistration().getData().getDob())
					.with(Account::setGender, GenderCodeEnum.fromValue(registrationRequest.getRegistration().getData().getGender()))
					.with(Account::setHeight, registrationRequest.getRegistration().getData().getHeightInCM())
					.with(Account::setFirstName, registrationRequest.getRegistration().getData().getFirstName())
					.with(Account::setLastName, registrationRequest.getRegistration().getData().getLastName())
					
					.with(Account::setUsername, registrationRequest.getRegistration().getEmailId())
					.with(Account::setActive, 1)
					.with(Account::setPassword, passwordEncoder.encode(registrationRequest.getRegistration().getPassword()))
					.with(Account::setRoles, "USER")
					.with(Account::setPermissions, "ACCESS_GET,ACCESS_POST,ACCESS_DELETE,ACCESS_PATCH")
					.build();

			ObjectMapper mapper = new ObjectMapper();
			mapper.writerWithDefaultPrettyPrinter();
			try {
				logger.debug("Mongo DB Object For Saving RegistrationRequest ==> {}", mapper.writeValueAsString(account));
			} catch (JsonProcessingException exp) {
				logger.error("Json Processing Exception while printing request ", exp);
			}
		}
		return account;

	}

	@Override
	public UpdateContactDetailsResponse initiateUserContactDetailsUpdation(final String accountID, final UpdateContactDetailsRequest updateContactDetailsRequest) {

		logger.info("Processing in AccountServiceImpl || initiateUserContactDetailsUpdation || AccountID {}", accountID);
		Optional<Account> account = accountRepository.findById(accountID);
		if (!account.isPresent())
			throw new BusinessException(HttpStatus.BAD_GATEWAY, errorResolver.getErrorMessage("VALIDATION_EXCEPTION"));

		ContactDetails contactDetails = account.get().getContactDetails();
		if (!Optional.ofNullable(contactDetails).isPresent())
			throw new BusinessException(HttpStatus.BAD_GATEWAY, errorResolver.getErrorMessage("VALIDATION_EXCEPTION"));

		contactDetails.setAlternateEmailId(updateContactDetailsRequest.getData().getAlternateEmailId());
		contactDetails.setAlternateMobileNumber(updateContactDetailsRequest.getData().getAlternateMobileNumber());
		contactDetails.setCity(updateContactDetailsRequest.getData().getCity());
		contactDetails.setContactAddress(updateContactDetailsRequest.getData().getContactAddress());
		contactDetails.setCountry(updateContactDetailsRequest.getData().getCountry());
		contactDetails.setLandlineNumber(updateContactDetailsRequest.getData().getLandlineNumber());
		contactDetails.setMobileNumber(updateContactDetailsRequest.getData().getMobileNumber());
		contactDetails.setParentsAddress(updateContactDetailsRequest.getData().getParentsAddress());
		contactDetails.setSuitableTimeToCall(updateContactDetailsRequest.getData().getSuitableTimeToCall());

		account.get().setContactDetails(contactDetails);
		Account accountDBObj = accountRepository.save(account.get());
		if (!Optional.ofNullable(accountDBObj).isPresent())
			throw new BusinessException(HttpStatus.BAD_GATEWAY, errorResolver.getErrorMessage("VALIDATION_EXCEPTION"));

		ContactDetailsResponse contactDetailsResponse = GenericBuilder.of(ContactDetailsResponse::new)
				.with(ContactDetailsResponse::setEmailId, accountDBObj.getContactDetails().getEmailId())
				.with(ContactDetailsResponse::setAlternateEmailId, accountDBObj.getContactDetails().getAlternateEmailId())
				.with(ContactDetailsResponse::setAlternateMobileNumber, accountDBObj.getContactDetails().getAlternateMobileNumber())
				.with(ContactDetailsResponse::setCity, accountDBObj.getContactDetails().getCity())
				.with(ContactDetailsResponse::setContactAddress, accountDBObj.getContactDetails().getContactAddress())
				.with(ContactDetailsResponse::setCountry, accountDBObj.getContactDetails().getCountry())
				.with(ContactDetailsResponse::setLandlineNumber, accountDBObj.getContactDetails().getLandlineNumber())
				.with(ContactDetailsResponse::setMobileNumber, accountDBObj.getContactDetails().getMobileNumber())
				.with(ContactDetailsResponse::setParentsAddress, accountDBObj.getContactDetails().getParentsAddress())
				.with(ContactDetailsResponse::setSuitableTimeToCall,accountDBObj.getContactDetails().getSuitableTimeToCall())
				.build();

		UpdateContactDetailsResponse updateContactDetailsResponse = GenericBuilder.of(UpdateContactDetailsResponse::new)
				.with(UpdateContactDetailsResponse::setData, contactDetailsResponse)
				.build();

		return updateContactDetailsResponse;
	}

	@Override
	public GetAccountDetailsResponse initiateGetAccountDetails(final String accountID) {

		logger.info("Request for removing the account Id :: {}", accountID);
		Optional<Account> dbObj = accountRepository.findById(accountID);
		if (!(dbObj).isPresent())
			throw new ResourceNotFoundException(HttpStatus.NOT_FOUND, errorResolver.getErrorMessage("RESOURCE_NOT_FOUND", accountID));
		
		Account account = dbObj.get();
		BasicDetails basicDetails = null;
		AboutMe aboutMe = null;
		EducationDetails educationDetails = null;
		CarrierDetails carrierDetails = null;
		FamilyDetails familyDetails = null;
		ContactDetail contactDetails = null;
		
		CriticalFields criticalFields = GenericBuilder.of(CriticalFields::new)
				.with(CriticalFields::setAge, String.valueOf(account.getAge()))
				.with(CriticalFields::setAccountId, account.getId())
				.with(CriticalFields::setMaritalStatus, account.getMaritalStatus() != null ? account.getMaritalStatus().toString() : null)
				.build();

		basicDetails = GenericBuilder.of(BasicDetails::new)
					.with(BasicDetails::setCity, account.getContactDetails().getCity())
					.with(BasicDetails::setCreateProfileFor, account.getCreateProfileFor() != null ? account.getCreateProfileFor().toString() : null)
					.with(BasicDetails::setFirstName, account.getFirstName())
					.with(BasicDetails::setHeight, account.getHeight())
					.with(BasicDetails::setLastName, account.getLastName())
					.with(BasicDetails::setMotherTongue, account.getMotherTongue() != null ? account.getMotherTongue().toString() : null)
					.with(BasicDetails::setReligion, account.getReligion() != null ? account.getReligion().toString() : null)
					.build();

		if (Optional.ofNullable(account.getPersonalDetails()).isPresent()) {

			aboutMe = GenericBuilder.of(AboutMe::new)
					.with(AboutMe::setAboutMe, account.getPersonalDetails().getOccupation())
					.with(AboutMe::setAboutMyFamily, account.getPersonalDetails().getAboutMyFamily())
					.with(AboutMe::setEducation, account.getPersonalDetails().getEducation())
					.with(AboutMe::setOccupation, account.getPersonalDetails().getOccupation())
					.build();
		}
		
		if (Optional.ofNullable(account.getEducationDetails()).isPresent()) {
			
			educationDetails = GenericBuilder.of(EducationDetails::new)
					.with(EducationDetails::setHighestEducation, account.getEducationDetails().getHighestEducation())
					.with(EducationDetails::setPgCollege, account.getEducationDetails().getPgCollege())
					.with(EducationDetails::setPgDegree, account.getEducationDetails().getPgDegree())
					.with(EducationDetails::setSchoolName, account.getEducationDetails().getSchoolName())
					.with(EducationDetails::setUgCollege, account.getEducationDetails().getUgCollege())
					.with(EducationDetails::setUgDegree, account.getEducationDetails().getUgDegree())
					.build();
		}
		
		if (Optional.ofNullable(account.getOccupationalDetails()).isPresent()) {
			
			carrierDetails = GenericBuilder.of(CarrierDetails::new)
					.with(CarrierDetails::setAnnualIncome, account.getOccupationalDetails().getAnnualIncome())
					.with(CarrierDetails::setEmployedIn, account.getOccupationalDetails().getEmployedIn())
					.with(CarrierDetails::setOccupation, account.getOccupationalDetails().getOccupation())
					.with(CarrierDetails::setOrganizationName, account.getOccupationalDetails().getOrganizationName())
					.build();
		}

		if (Optional.ofNullable(account.getFamilyDetails()).isPresent()) {
			
			familyDetails = GenericBuilder.of(FamilyDetails::new)
					.with(FamilyDetails::setCity, account.getContactDetails().getCity())
					.with(FamilyDetails::setFamilyBasedOutOf, account.getFamilyDetails().getFamilyBasedOutOf())
					.with(FamilyDetails::setFamilyIncome, account.getFamilyDetails().getFamilyIncome())
					.with(FamilyDetails::setFamilyStatus, account.getFamilyDetails().getFamilyStatus())
					.with(FamilyDetails::setFamilyValues, account.getFamilyDetails().getFamilyValues())
					.with(FamilyDetails::setFathersOccupation, account.getFamilyDetails().getFathersOccupation())
					.with(FamilyDetails::setGothra, account.getFamilyDetails().getGothra())
					.with(FamilyDetails::setLivingWithParents, account.getFamilyDetails().isLivingWithParents())
					.with(FamilyDetails::setMothersOccupation, account.getFamilyDetails().getMothersOccupation())
					.with(FamilyDetails::setNoOfBrothers, account.getFamilyDetails().getNoOfBrothers())
					.with(FamilyDetails::setNoOfSisters, account.getFamilyDetails().getNoOfSisters())
					.build();
		}
		
			
		contactDetails = GenericBuilder.of(ContactDetail::new)
					.with(ContactDetail::setAlternateEmailId, account.getContactDetails().getAlternateEmailId())
					.with(ContactDetail::setAlternateMobileNumber, account.getContactDetails().getAlternateMobileNumber())
					.with(ContactDetail::setCity, account.getContactDetails().getCity())
					.with(ContactDetail::setContactAddress, account.getContactDetails().getContactAddress())
					.with(ContactDetail::setCountry, account.getContactDetails().getCountry())
					.with(ContactDetail::setEmailId, account.getContactDetails().getEmailId())
					.with(ContactDetail::setLandlineNumber, account.getContactDetails().getLandlineNumber())
					.with(ContactDetail::setMobileNumber, account.getContactDetails().getMobileNumber())
					.with(ContactDetail::setParentsAddress, account.getContactDetails().getParentsAddress())
					.with(ContactDetail::setSuitableTimeToCall, account.getContactDetails().getSuitableTimeToCall())
					.build();

		GetAccountDetailsResponse getAccountDetailsResponse = GenericBuilder.of(GetAccountDetailsResponse::new)
				.with(GetAccountDetailsResponse::setAboutMe, aboutMe)
				.with(GetAccountDetailsResponse::setBasicDetails, basicDetails)
				.with(GetAccountDetailsResponse::setCarrierDetails, carrierDetails)
				.with(GetAccountDetailsResponse::setContactDetails, contactDetails)
				.with(GetAccountDetailsResponse::setCriticalFields, criticalFields)
				.with(GetAccountDetailsResponse::setEducationDetails, educationDetails)
				.with(GetAccountDetailsResponse::setFamilyDetails, familyDetails)
				.build();
		
		return getAccountDetailsResponse;
	}

	@Override
	public boolean removeAccount(final String accountID) {

		logger.info("Request for removing the account Id :: {}", accountID);
		Optional<Account> dbObj = accountRepository.findById(accountID);
		if (!(dbObj).isPresent())
			throw new ResourceNotFoundException(HttpStatus.NOT_FOUND, errorResolver.getErrorMessage("RESOURCE_NOT_FOUND_TO_DELETE", accountID));

		accountRepository.delete(dbObj.get());
		return Boolean.TRUE;
	}

}
