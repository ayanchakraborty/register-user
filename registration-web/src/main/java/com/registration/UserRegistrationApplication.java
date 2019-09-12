package com.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages={"com.registration"})
@EnableJpaRepositories(basePackages = {"com.registration.dao"})
public class UserRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserRegistrationApplication.class, args);
	}

}
