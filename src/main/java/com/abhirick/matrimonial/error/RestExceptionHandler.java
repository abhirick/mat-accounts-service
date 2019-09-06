/**
 *
 */
package com.abhirick.matrimonial.error;

import static org.owasp.encoder.Encode.forJava;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.abhirick.matrimonial.builder.GenericBuilder;
import com.abhirick.matrimonial.model.Error;
import com.abhirick.matrimonial.model.ErrorResponse;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler {

	private static final String COMMON_EXP_MSG = "An error has occurred. Please see the details in the 'Errors' array for further detail.";

	private static final String MATRIMONY_ACCOUNT_RESOURCE_NOT_FOUND = "MATRIMONY.ACCOUNT.Resource.NotFound";

	private static final String MATRIMONY_ACCOUNT_UN_EXPECTED_ERROR = "MATRIMONY.ACCOUNT.UnExpectedError";

	private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {

		logger.error("ResourceNotFoundException caught in RestExceptionHandler {}", forJava(ex.getMessage()));

		List<Error> errorList = new ArrayList<>();
		Error childError = GenericBuilder.of(Error::new)
				.with(Error::setErrorCode, MATRIMONY_ACCOUNT_RESOURCE_NOT_FOUND)
				.with(Error::setMessage, ex.getMessage())
				.build();

		errorList.add(childError);

		ErrorResponse errorResponse = GenericBuilder.of(ErrorResponse::new)
				.with(ErrorResponse::setCode, String.format("%d", HttpStatus.BAD_REQUEST.value()))
				.with(ErrorResponse::setId, UUID.randomUUID().toString())
				.with(ErrorResponse::setMessage, COMMON_EXP_MSG)
				.with(ErrorResponse::setErrors, errorList)
				.build();

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {

		logger.error("BusinessException caught in RestExceptionHandler {}", forJava(ex.getMessage()));

		List<Error> errorList = new ArrayList<>();
		Error childError = GenericBuilder.of(Error::new)
				.with(Error::setErrorCode, MATRIMONY_ACCOUNT_UN_EXPECTED_ERROR)
				.with(Error::setMessage, ex.getMessage())
				.build();

		errorList.add(childError);

		ErrorResponse errorResponse = GenericBuilder.of(ErrorResponse::new)
				.with(ErrorResponse::setCode, String.format("%d", HttpStatus.INTERNAL_SERVER_ERROR.value()))
				.with(ErrorResponse::setId, UUID.randomUUID().toString())
				.with(ErrorResponse::setMessage, COMMON_EXP_MSG)
				.with(ErrorResponse::setErrors, errorList)
				.build();

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex) {

		logger.error("ValidationException caught in RestExceptionHandler", forJava(ex.getMessage()));

		List<Error> errorList = new ArrayList<>();
		Error childError = GenericBuilder.of(Error::new)
				.with(Error::setErrorCode, MATRIMONY_ACCOUNT_UN_EXPECTED_ERROR)
				.with(Error::setMessage, ex.getMessage())
				.build();

		errorList.add(childError);

		ErrorResponse errorResponse = GenericBuilder.of(ErrorResponse::new)
				.with(ErrorResponse::setCode, String.format("%d", HttpStatus.INTERNAL_SERVER_ERROR.value()))
				.with(ErrorResponse::setId, UUID.randomUUID().toString())
				.with(ErrorResponse::setMessage, COMMON_EXP_MSG)
				.with(ErrorResponse::setErrors, errorList)
				.build();


		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

		logger.error("MethodArgumentNotValidException caught in RestExceptionHandler {}", forJava(ex.getMessage()));

		List<Error> errorList = new ArrayList<>();
		BindingResult bindingResult = ex.getBindingResult();

		List<ObjectError> errors = bindingResult.getAllErrors();
		if (!CollectionUtils.isEmpty(errors)) {
			errors.forEach(error -> {

				if (error instanceof FieldError) {

					FieldError fe = (FieldError) error;

					Error childError = GenericBuilder.of(Error::new)
							.with(Error::setErrorCode, HttpStatus.BAD_REQUEST.toString())
							.with(Error::setMessage, fe.getDefaultMessage())
							.with(Error::setPath, fe.getField())
							.build();

					String[] codes = fe.getCodes();
					String message = codes[codes.length - 1];
					childError.setErrorCode(message);

					if (StringUtils.isEmpty(childError.getMessage())) {
						childError.setMessage(message);
					}

					errorList.add(childError);
				}

			});
		}

		ErrorResponse errorResponse = GenericBuilder.of(ErrorResponse::new)
				.with(ErrorResponse::setCode, String.format("%d", HttpStatus.BAD_REQUEST.value()))
				.with(ErrorResponse::setMessage, COMMON_EXP_MSG)
				.with(ErrorResponse::setId, UUID.randomUUID().toString())
				.with(ErrorResponse::setErrors, errorList).build();

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception ex) {
		
		if (ex instanceof MissingRequestHeaderException) {
			logger.error("MissingRequestHeaderException caught in RestExceptionHandler {}", forJava(ex.getMessage()));
		} else {
			logger.error("Exception caught in RestExceptionHandler {}", forJava(ex.getMessage()));
			logger.error("Exception StackTrace ::  {}", ex);
		}
		
		List<Error> errorList = new ArrayList<>();
		Error childError = GenericBuilder.of(Error::new)
				.with(Error::setErrorCode, MATRIMONY_ACCOUNT_UN_EXPECTED_ERROR)
				.with(Error::setMessage, ex.getMessage())
				.build();
		
		errorList.add(childError);

		ErrorResponse errorResponse = GenericBuilder.of(ErrorResponse::new)
				.with(ErrorResponse::setCode, String.format("%d", HttpStatus.INTERNAL_SERVER_ERROR.value()))
				.with(ErrorResponse::setId, UUID.randomUUID().toString())
				.with(ErrorResponse::setMessage, COMMON_EXP_MSG)
				.with(ErrorResponse::setErrors, errorList)
				.build();

		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
