/**
 * 
 */
package com.abhirick.matrimonial.validator;

import org.springframework.validation.Validator;

public interface RequestValidator extends Validator {

	String getValidator();
}
