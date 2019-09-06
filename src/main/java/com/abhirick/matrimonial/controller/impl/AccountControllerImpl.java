/**
 *
 */
package com.abhirick.matrimonial.controller.impl;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhirick.matrimonial.aspect.RequestLog;
import com.abhirick.matrimonial.aspect.Timed;
import com.abhirick.matrimonial.constants.AccountsConstants;
import com.abhirick.matrimonial.controller.AccountController;
import com.abhirick.matrimonial.error.BusinessException;
import com.abhirick.matrimonial.error.ErrorResolver;
import com.abhirick.matrimonial.error.ResourceNotFoundException;
import com.abhirick.matrimonial.error.ValidationException;
import com.abhirick.matrimonial.factory.RequestFactory;
import com.abhirick.matrimonial.model.GetAccountDetailsResponse;
import com.abhirick.matrimonial.model.RegistrationRequest;
import com.abhirick.matrimonial.model.RegistrationResponse;
import com.abhirick.matrimonial.model.UpdateContactDetailsRequest;
import com.abhirick.matrimonial.model.UpdateContactDetailsResponse;
import com.abhirick.matrimonial.service.impl.AccountService;
import com.abhirick.matrimonial.utils.ResponseHeaderBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "api/v1.0/accounts", produces = { "application/json" })
@Validated
public class AccountControllerImpl implements AccountController {

	private final Logger log = LoggerFactory.getLogger(AccountControllerImpl.class);

	@Autowired
	private ErrorResolver errorResolver;
	
	@Autowired
	private AccountService accountService;

	@InitBinder("registrationRequest")
	protected void initPaymentReqBinder(WebDataBinder binder) {
		binder.setValidator(RequestFactory.getValidators("RegistrationRequest"));
	}

	@RequestLog
	@Timed
	@Override
	public ResponseEntity<RegistrationResponse> registerUser(final RegistrationRequest registrationRequest, final BindingResult bindingResult) {

		log.info("Processing Registered User API.");
		if (bindingResult.hasErrors())
			throw new ValidationException(HttpStatus.BAD_REQUEST, errorResolver.getErrorMessage(AccountsConstants.VALIDATION_EXCEPTION), bindingResult);

		ObjectMapper mapper = new ObjectMapper();
		mapper.writerWithDefaultPrettyPrinter();
		try {
			if (log.isDebugEnabled()) {
				log.debug("Registration API POST Request ==> {}", mapper.writeValueAsString(registrationRequest));
			}
		} catch (JsonProcessingException exp) {
			log.error("Json Processing Exception while printing request ", exp);
		}
		
		RegistrationResponse response = this.accountService.initiateUserRegistration(registrationRequest);
		return new ResponseEntity(response, ResponseHeaderBuilder.createHeaders(OffsetDateTime.now(ZoneOffset.UTC)), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<UpdateContactDetailsResponse> updateContactDetails(final String accountID, final UpdateContactDetailsRequest updateContactDetailsRequest, final BindingResult bindingResult) {

		log.info("Processing Update Contact Details for User API. {}", accountID);
		if (bindingResult.hasErrors())
			throw new ValidationException(HttpStatus.BAD_REQUEST, errorResolver.getErrorMessage(AccountsConstants.VALIDATION_EXCEPTION), bindingResult);

		ObjectMapper mapper = new ObjectMapper();
		mapper.writerWithDefaultPrettyPrinter();
		try {
			if (log.isDebugEnabled()) {
				log.debug("Update Contact Details API PUT Request ==> {}", mapper.writeValueAsString(updateContactDetailsRequest));
			}
		} catch (JsonProcessingException exp) {
			log.error("Json Processing Exception while printing request ", exp);
		}
		
		UpdateContactDetailsResponse response = this.accountService.initiateUserContactDetailsUpdation(accountID, updateContactDetailsRequest);
		return new ResponseEntity(response, ResponseHeaderBuilder.createHeaders(OffsetDateTime.now(ZoneOffset.UTC)), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<GetAccountDetailsResponse> getAccountDetailsByAccountId(final String accountID) {

		log.info("Processing GET Account Details for User : {}", accountID);
		if (StringUtils.isEmpty(accountID))
			throw new ValidationException(HttpStatus.BAD_REQUEST, errorResolver.getErrorMessage(AccountsConstants.VALIDATION_EXCEPTION, accountID));

		GetAccountDetailsResponse response = this.accountService.initiateGetAccountDetails(accountID);
		if (!Optional.ofNullable(response).isPresent())
			throw new ResourceNotFoundException(HttpStatus.NOT_FOUND, errorResolver.getErrorMessage("RESOURCE_NOT_FOUND", accountID));

		return new ResponseEntity(response, ResponseHeaderBuilder.createHeaders(OffsetDateTime.now(ZoneOffset.UTC)), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteAccountByAccountId(final String accountID) {
		
		log.info("Delete a Account record by accountID {}", accountID);
		if (this.accountService.removeAccount(accountID)) {
			return new ResponseEntity(ResponseHeaderBuilder.createHeaders(OffsetDateTime.now(ZoneOffset.UTC)), HttpStatus.NO_CONTENT);
		} else {
			throw new BusinessException(HttpStatus.BAD_GATEWAY, errorResolver.getErrorMessage(AccountsConstants.VALIDATION_EXCEPTION, accountID));
		}
	}

}
