/**
 *
 */
package com.abhirick.matrimonial.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.abhirick.matrimonial.model.ErrorResponse;
import com.abhirick.matrimonial.model.GetAccountDetailsResponse;
import com.abhirick.matrimonial.model.RegistrationRequest;
import com.abhirick.matrimonial.model.RegistrationResponse;
import com.abhirick.matrimonial.model.UpdateContactDetailsRequest;
import com.abhirick.matrimonial.model.UpdateContactDetailsResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Matrimonial Account API's.", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public interface AccountController {

	@ApiOperation(value = "POST API for Matrimonial Account Registration.", notes = "POST API for Matrimonial Account Registration.", response = RegistrationResponse.class, tags = {"Matrimonial Account API's." })
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Registered Successfully.", response = RegistrationResponse.class),
			@ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized"), 
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not found"), 
			@ApiResponse(code = 405, message = "Method Not Allowed"),
			@ApiResponse(code = 406, message = "Not Acceptable"),
			@ApiResponse(code = 415, message = "Unsupported Media Type"),
			@ApiResponse(code = 429, message = "Too Many Requests"),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Void.class) })

	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResponseEntity<RegistrationResponse> registerUser(@RequestBody @Validated RegistrationRequest registrationRequest, BindingResult bindingResult);
	
	
	@ApiOperation(value = "PATCH Account's Contact Details records by accountID", notes = "Update Matrimonial Account's Contact Detail records by accountID. Received consent will be Authorised.", response = UpdateContactDetailsResponse.class, tags = {"Matrimonial Account API's."})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Account Details Updated/Patched", response = UpdateContactDetailsResponse.class),
			@ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 405, message = "Method Not Allowed"),
			@ApiResponse(code = 406, message = "Not Acceptable"),
			@ApiResponse(code = 429, message = "Too Many Requests"),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	
	@PatchMapping(path = "/{accountID}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	
	ResponseEntity<UpdateContactDetailsResponse> updateContactDetails(@PathVariable("accountID") String accountID, @RequestBody @Validated UpdateContactDetailsRequest updateContactDetailsRequest,
			BindingResult bindingResult);
	
	
	@ApiOperation(value = "GET Account Details records by accountID", notes = "Fetch Account Details based on accountID", response = GetAccountDetailsResponse.class, tags = {"Matrimonial Account API's."})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Account Details Read Successfully.", response = GetAccountDetailsResponse.class),
			@ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 405, message = "Method Not Allowed"),
			@ApiResponse(code = 406, message = "Not Acceptable"),
			@ApiResponse(code = 429, message = "Too Many Requests"),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	
	@GetMapping(path = "/{accountID}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResponseEntity<GetAccountDetailsResponse> getAccountDetailsByAccountId(@PathVariable("accountID") String accountID);
	
	
	@ApiOperation(value = "Delete a Account record by accountID.", notes = "Delete a Account record by consentId.", response = Void.class, tags = {"Matrimonial Account API's."})
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Delated Account Successfully", response = Void.class),
			@ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 405, message = "Method Not Allowed"),
			@ApiResponse(code = 406, message = "Not Acceptable"),
			@ApiResponse(code = 429, message = "Too Many Requests"),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	
	@DeleteMapping(path = "/{accountID}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResponseEntity<?> deleteAccountByAccountId(@PathVariable("accountID") String accountID);


	
}
