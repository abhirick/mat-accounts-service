/**
 *
 */
package com.abhirick.matrimonial.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends BaseException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String pMessage) {
		super(pMessage);
	}

	public ResourceNotFoundException(HttpStatus status, String errorMessage) {
		super(status, errorMessage);
	}
}
