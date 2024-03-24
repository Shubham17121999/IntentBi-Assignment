package com.intentbi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
public class IntentBi1Application {

	public static void main(String[] args) {
		SpringApplication.run(IntentBi1Application.class, args);
	}
	
	@Bean
	public SecurityFilterChain anyMethodName(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(
				auth -> auth.requestMatchers(HttpMethod.POST, "/products").permitAll()
				.anyRequest().authenticated())
		.csrf(c -> c.disable())
		.formLogin(Customizer.withDefaults())
		.httpBasic(Customizer.withDefaults());
		
		return http.build();
	}
}
