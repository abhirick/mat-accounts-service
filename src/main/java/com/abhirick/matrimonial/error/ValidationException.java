package com.abhirick.matrimonial.error;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationException extends BaseException {

	private static final long serialVersionUID = 1L;

	ValidationException(HttpStatus status) {
		super(status);
	}

	public ValidationException(HttpStatus status, String errorMessage, BindingResult bindingResult) {
		 super(status, errorMessage, bindingResult);
	}

	public ValidationException(HttpStatus status, String errorMessage) {
		super(status, errorMessage);
	}

	
}
