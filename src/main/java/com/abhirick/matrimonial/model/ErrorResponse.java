/**
 *
 */
package com.abhirick.matrimonial.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ErrorResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("Code")
	private String code;

	@JsonProperty("Id")
	private String id;

	@JsonProperty("Message")
	private String message;

	@JsonProperty("Details")
	private String details;

	@JsonProperty("Errors")
	private List<Error> errors = new ArrayList<>();

	public ErrorResponse code(String code) {
		this.code = code;
		return this;
	}

	/**
	 * High level textual error code, to help categorize the errors.
	 * 
	 * @return code
	 **/
	@ApiModelProperty(required = true, value = "High level textual error code, to help categorize the errors.")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ErrorResponse id(String id) {
		this.id = id;
		return this;
	}

	/**
	 * A unique reference for the error instance, for audit purposes, in case of
	 * unknown/unclassified errors.
	 * 
	 * @return id
	 **/
	@ApiModelProperty(value = "A unique reference for the error instance, for audit purposes, in case of unknown/unclassified errors.")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ErrorResponse message(String message) {
		this.message = message;
		return this;
	}

	/**
	 * Brief Error message, e.g., 'There is something wrong with the request
	 * parameters provided'
	 * 
	 * @return message
	 **/
	@ApiModelProperty(required = true, value = "Brief Error message, e.g., 'There is something wrong with the request parameters provided'")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorResponse errors(List<Error> errors) {
		this.errors = errors;
		return this;
	}

	public ErrorResponse addErrorsItem(Error errorsItem) {
		this.errors.add(errorsItem);
		return this;
	}

	/**
	 * Get errors
	 * 
	 * @return errors
	 **/
	@ApiModelProperty(required = true, value = "")
	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
