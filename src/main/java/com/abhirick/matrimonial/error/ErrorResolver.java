package com.abhirick.matrimonial.error;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class ErrorResolver {

	private Map<String, String> errorMsgMap;

	public String getErrorMessage(String errMsg) {

		return errorMsgMap.get(errMsg);
	}

	public String getErrorMessage(String errMsg, String input) {

		return MessageFormat.format(errorMsgMap.get(errMsg), input);
	}

	public String getErrorMessage(String errMsg, List<String> input) {
		StringBuilder errorMsg = new StringBuilder();
		for (String s : input) {
			errorMsg.append(MessageFormat.format(errorMsgMap.get(errMsg), s));
		}
		return errorMsg.toString();
	}

	public Map<String, String> getErrorMsgMap() {
		return errorMsgMap;
	}

	public void setErrorMsgMap(Map<String, String> errorMsgMap) {
		this.errorMsgMap = errorMsgMap;
	}

}
