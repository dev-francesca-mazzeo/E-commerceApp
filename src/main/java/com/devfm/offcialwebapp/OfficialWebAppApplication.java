package com.devfm.offcialwebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class OfficialWebAppApplication {

	public static void main(String[] args) {
		// Start the Spring Boot application
		SpringApplication.run(OfficialWebAppApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		// Create and return an instance of BCryptPasswordEncoder as a Spring Bean
		return new BCryptPasswordEncoder();
	}
}
