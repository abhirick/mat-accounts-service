package com.abhirick.matrimonial;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import com.abhirick.matrimonial.model.Account;
import com.abhirick.matrimonial.repository.AccountRepository;
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
public class AccountsApplication implements CommandLineRunner {

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

	@Autowired
	private AccountRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;


	@Override
	public void run(String... args) throws Exception {
		this.userRepository.deleteAll();

		Account dan = new Account("dan", passwordEncoder.encode("dan123"), "USER", "ACCESS_GET");
		Account admin = new Account("admin", passwordEncoder.encode("admin123"), "ADMIN", "ACCESS_GET,ACCESS_PUT,ACCESS_DELETE");
		Account manager = new Account("manager", passwordEncoder.encode("manager123"), "MANAGER", "ACCESS_TEST1");

		List<Account> users = Arrays.asList(dan, admin, manager);

		this.userRepository.saveAll(users);
			
	}

}
