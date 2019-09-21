package com.abhirick.matrimonial.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.abhirick.matrimonial.model.RegistrationRequest;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

	@Override
	public void initialize(final PasswordMatches constraintAnnotation) {
	}

	@Override
	public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
		final RegistrationRequest user = (RegistrationRequest) obj;
		return user.getRegistration().getPassword().equals(user.getRegistration().getMatchingPassword());
	}

}