package com.fdmgroup.BankingApplication.jwtsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private final Jwtfilter jwtFilter;
	private final CustomUserDetailsService detailsService;

	public SecurityConfig(CustomUserDetailsService detailsService, Jwtfilter jwtfilter) {
		this.jwtFilter = jwtfilter;
		this.detailsService = detailsService;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth
				.requestMatchers("/auth/**").permitAll()
				.requestMatchers("/api/v1/employees/**").hasRole("MANAGER")
				.requestMatchers("/api/v1/customers/**").hasRole("TELLER")
				.requestMatchers("/api/v1/accounts/**").hasAnyRole("MANAGER", "TELLER")
				.anyRequest().authenticated())
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
				.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))
				.authenticationProvider(authenticationProvider()).httpBasic(Customizer.withDefaults())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

		// Set UserDetailsService
		provider.setUserDetailsService(detailsService);

		// Set PasswordEncoder
		provider.setPasswordEncoder(passwordEncoder());

		// Remove "ROLE_" prefix from roles
		SimpleAuthorityMapper authorityMapper = new SimpleAuthorityMapper();
		authorityMapper.setPrefix(""); // No prefix for roles
		authorityMapper.setConvertToUpperCase(true); // Optional: ensures role names are uppercase
		provider.setAuthoritiesMapper(authorityMapper);

		return provider;
	}
}