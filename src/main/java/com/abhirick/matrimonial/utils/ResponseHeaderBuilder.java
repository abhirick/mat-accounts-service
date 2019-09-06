package com.abhirick.matrimonial.utils;

import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.http.HttpHeaders;

public class ResponseHeaderBuilder {

	private ResponseHeaderBuilder() {
	}

	public static HttpHeaders createHeaders(final OffsetDateTime dateTime) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("x-CreateDateTime", dateTime.toString());

		return headers;
	}

	public static HttpHeaders createHeaders(final UUID journeyId, final String sourceSystemId,
			final String xRequesterType, final String xRequesterID, final String xAPIVersion) {
		HttpHeaders headers = new HttpHeaders();

		return headers;
	}

}
