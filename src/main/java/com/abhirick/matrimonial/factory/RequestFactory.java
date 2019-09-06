package com.abhirick.matrimonial.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.abhirick.matrimonial.error.BusinessException;
import com.abhirick.matrimonial.validator.RequestValidator;

@Component
public class RequestFactory {

	@Autowired
	private List<RequestValidator> validators;

	public List<RequestValidator> getValidators() {
		return validators;
	}

	private static final Map<String, RequestValidator> requestCache = new HashMap<>();

	@PostConstruct
	public void initRequestCache() {
		for (RequestValidator validator : getValidators()) {
			String validate = StringUtils.trim(validator.getValidator());
			if (!requestCache.containsKey(validate)) {
				requestCache.put(validate, validator);
			}
		}
	}

	public static RequestValidator getValidators(String validate) {
		RequestValidator validator = requestCache.get(validate);
		if (validator == null)
			throw new BusinessException(HttpStatus.BAD_REQUEST, "Validator not available" + validate);
		return validator;
	}
}