package com.abhirick.matrimonial.error;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

public class BusinessException extends BaseException {

	private static final long serialVersionUID = 1L;

	BusinessException(HttpStatus status) {
		super(status);
	}

	public BusinessException(HttpStatus status, String errorMessage, BindingResult bindingResult) {
		super(status, errorMessage, bindingResult);
	}

	public BusinessException(HttpStatus status, String errorMessage) {
		super(status, errorMessage);
	}

}
