package com.abhirick.matrimonial.error;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BaseException extends RuntimeException {

	private static final long serialVersionUID = -3770003796986186393L;

	private HttpStatus status;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;

	private String message;

	private String path;

	private String url;

	private BindingResult validationErrors;

	private String debugMessage;

	private BaseException() {
		timestamp = LocalDateTime.now();
	}

	BaseException(HttpStatus status) {
		this();
		this.status = status;
	}

	BaseException(HttpStatus status, Throwable ex) {
		this();
		this.status = status;
		this.message = "Unexpected error";
		this.debugMessage = ex.getLocalizedMessage();
	}

	public BaseException(HttpStatus status, String errorMessage) {
		this();
		this.status = status;
		this.message = errorMessage;
	}

	BaseException(HttpStatus status, String message, Throwable ex) {
		this();
		this.status = status;
		this.message = message;
		this.debugMessage = ex.getLocalizedMessage();
	}

	BaseException(HttpStatus status, String message, BindingResult validationErrors) {

		super(message);
		this.status = status;
		this.message = message;
		this.validationErrors = validationErrors;
	}

	public BaseException(String message, String path, String url) {
		super(message);
		this.message = message;
		this.path = path;
		this.url = url;
	}

	public BaseException(String message, String path, String url, BindingResult validationErrors) {
		super(message);
		this.message = message;
		this.path = path;
		this.url = url;
		this.validationErrors = validationErrors;
	}

	public BaseException(String message, String path, String url, Throwable e) {
		super(message, e);
		this.message = message;
		this.path = path;
		this.url = url;
	}

	public BaseException(String pMessage) {
		super(pMessage);
	}

	public BaseException(String pMessage, BindingResult validationErrors) {
		super(pMessage);
		this.validationErrors = validationErrors;
	}

	public BaseException(String pMessage, Throwable e) {
		super(pMessage, e);
		validationErrors = null;
	}

	public BaseException(Throwable e) {
		super(e);
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public BindingResult getValidationErrors() {
		return validationErrors;
	}

	public void setValidationErrors(BindingResult validationErrors) {
		this.validationErrors = validationErrors;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getDebugMessage() {
		return debugMessage;
	}

	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}

}
