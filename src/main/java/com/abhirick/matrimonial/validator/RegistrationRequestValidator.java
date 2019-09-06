package com.abhirick.matrimonial.validator;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.abhirick.matrimonial.model.RegistrationRequest;
import com.abhirick.matrimonial.model.RegistrationRequest.Registration;
import com.abhirick.matrimonial.model.RegistrationRequest.Registration.AccountData;


@Component
public class RegistrationRequestValidator implements RequestValidator {

	@Override
	public boolean supports(Class<?> aClass) {
		return RegistrationRequest.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {

		RegistrationRequest request = (RegistrationRequest) o;

		if (Optional.ofNullable(request.getRegistration()).isPresent()) {
			
			Registration registration =  request.getRegistration();
			errors.pushNestedPath("registration");
			
			ValidationUtils.rejectIfEmpty(errors, "emailId", "MATRIMONY.ACCOUNT.Field.Missing", "emailId is empty");
			ValidationUtils.rejectIfEmpty(errors, "password", "MATRIMONY.ACCOUNT.Field.Missing", "password is empty");
			if (!StringUtils.isEmpty(request.getRegistration().getPassword()) && request.getRegistration().getPassword().length() < 8) {
				errors.rejectValue("password", "MATRIMONY.ACCOUNT.Field.InvalidPassword", "The input data password does not match the required criteria.");
			}

			if (Optional.ofNullable(request.getRegistration().getData()).isPresent()) {

				AccountData data =  request.getRegistration().getData();
				errors.pushNestedPath("data");
				
				ValidationUtils.rejectIfEmpty(errors, "firstName", "MATRIMONY.ACCOUNT.Field.Missing", "firstName is empty");
				ValidationUtils.rejectIfEmpty(errors, "lastName", "MATRIMONY.ACCOUNT.Field.Missing", "lastName is empty");
				ValidationUtils.rejectIfEmpty(errors, "city", "MATRIMONY.ACCOUNT.Field.Missing", "city is empty");
				ValidationUtils.rejectIfEmpty(errors, "mobileNumber", "MATRIMONY.ACCOUNT.Field.Missing", "mobileNumber is empty");

				ValidationUtils.rejectIfEmpty(errors, "dob", "MATRIMONY.ACCOUNT.Field.Missing", "dob is empty");
				if (Optional.ofNullable(request.getRegistration().getData().getDob()).isPresent()) {
					Date expTime = request.getRegistration().getData().getDob();
					if (expTime.before(new Date())) {
						errors.rejectValue("dob", "MATRIMONY.ACCOUNT.Field.InvalidDate", "The input data dob is invalid.");
					}
				}
				errors.popNestedPath();
			} else {
				errors.rejectValue("data", "MATRIMONY.ACCOUNT.Field.InvalidRequest", "The input field data cannot be null.");
			}
			errors.popNestedPath();
		} else {
			errors.rejectValue("registration", "MATRIMONY.ACCOUNT.Field.InvalidRequest", "The input data registration cannot be null.");
		}
	}

	@Override
	public String getValidator() {
		return "RegistrationRequest";
	}
}