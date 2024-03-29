package com.abhirick.matrimonial.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AccountsConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(AccountsConfiguration.class);

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();

	}

}
