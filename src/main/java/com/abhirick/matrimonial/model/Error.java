/**
 *
 */
package com.abhirick.matrimonial.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Error implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("ErrorCode")
	private String errorCode;

	@JsonProperty("Message")
	private String message;

	@JsonProperty("Path")
	private String path;

	@JsonProperty("Url")
	private String url;

	public Error errorCode(String errorCode) {
		this.errorCode = errorCode;
		return this;
	}

	/**
	 * Low level textual error code, e.g., UK.OBIE.Field.Missing
	 * 
	 * @return errorCode
	 **/
	@ApiModelProperty(required = true, value = "Low level textual error code, e.g., UK.OBIE.Field.Missing")
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public Error message(String message) {
		this.message = message;
		return this;
	}

	/**
	 * A description of the error that occurred. e.g., 'A mandatory field isn't
	 * supplied' or 'RequestedExecutionDateTime must be in future' OBIE doesn't
	 * standardise this field
	 * 
	 * @return message
	 **/
	@ApiModelProperty(required = true, value = "A description of the error that occurred. e.g., 'A mandatory field isn't supplied' or 'RequestedExecutionDateTime must be in future' OBIE doesn't standardise this field")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Error path(String path) {
		this.path = path;
		return this;
	}

	/**
	 * Recommended but optional reference to the JSON Path of the field with error,
	 * e.g., Data.Initiation.InstructedAmount.Currency
	 * 
	 * @return path
	 **/
	@ApiModelProperty(value = "Recommended but optional reference to the JSON Path of the field with error, e.g., Data.Initiation.InstructedAmount.Currency")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Error url(String url) {
		this.url = url;
		return this;
	}

	/**
	 * URL to help remediate the problem, or provide more information, or to API
	 * Reference, or help etc
	 * 
	 * @return url
	 **/
	@ApiModelProperty(value = "URL to help remediate the problem, or provide more information, or to API Reference, or help etc")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
