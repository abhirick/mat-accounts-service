package com.abhirick.matrimonial.service.impl;

import com.abhirick.matrimonial.model.GetAccountDetailsResponse;
import com.abhirick.matrimonial.model.RegistrationRequest;
import com.abhirick.matrimonial.model.RegistrationResponse;
import com.abhirick.matrimonial.model.UpdateContactDetailsRequest;
import com.abhirick.matrimonial.model.UpdateContactDetailsResponse;

public interface AccountService {

	RegistrationResponse initiateUserRegistration(final RegistrationRequest registrationRequest);

	UpdateContactDetailsResponse initiateUserContactDetailsUpdation(final String accountID, final UpdateContactDetailsRequest updateContactDetailsRequest);

	GetAccountDetailsResponse initiateGetAccountDetails(final String accountID);

	boolean removeAccount(final String accountID);

}
