package com.intentbi.Config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class AppConfig {

	private UserDetailsService userDetailsService;

	private JwtAuthenticationEntryPoint authenticationEntryPoint;

	private JwtAuthenticationFilter authenticationFilter;

	public AppConfig(UserDetailsService userDetailsService, JwtAuthenticationEntryPoint authenticationEntryPoint,
			JwtAuthenticationFilter authenticationFilter) {
		this.userDetailsService = userDetailsService;
		this.authenticationEntryPoint = authenticationEntryPoint;
		this.authenticationFilter = authenticationFilter;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain configration(HttpSecurity http) throws Exception {
		http.sessionManagement(
				sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.cors(cors -> {

					cors.configurationSource(new CorsConfigurationSource() {

						@Override
						public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

							CorsConfiguration cfg = new CorsConfiguration();

							cfg.setAllowedOriginPatterns(Collections.singletonList("*"));
							cfg.setAllowedMethods(Collections.singletonList("*"));
							cfg.setAllowCredentials(true);
							cfg.setAllowedHeaders(Collections.singletonList("*"));
							cfg.setExposedHeaders(Arrays.asList("Authorization"));
							return cfg;

						}
					});		

				})
				.authorizeHttpRequests(auth -> auth.requestMatchers("/swagger-ui*/**", "/v3/api-docs/**").permitAll()
						.requestMatchers("user/signin", "/user/register","/products/**").permitAll())
				
				.csrf(c -> c.disable())

		;
		http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
		;

		return http.build();

	}

}