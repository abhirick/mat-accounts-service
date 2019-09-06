package com.abhirick.matrimonial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.RestController;

import com.abhirick.matrimonial.repository.mongo.events.LoggingEventListener;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "com.abhirick.matrimonial" })
public class AccountsApplication {

	private final Logger log = LoggerFactory.getLogger(AccountsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
				.build();
	}

	@Bean
	LoggingEventListener listener() {
		return new LoggingEventListener();
	}

	@Bean
	public CommandLineRunner sample() {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				log.info("Command Line");
			}
		};
	}

}
