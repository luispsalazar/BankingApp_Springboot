package com.fdmgroup.BankingApplication.jwtsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	private JwtUtil jwtUtil;
	private CustomUserDetailsService userDetailsService;
	private AuthenticationManager authenticationManager;

	@Autowired
	public AuthenticationController(JwtUtil jwtUtil, CustomUserDetailsService userDetailsService,
			AuthenticationManager authenticationManager) {
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
		this.authenticationManager = authenticationManager;
	}

	@PostMapping("/login")
	public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request) {
		try {
			// Authenticate the user
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

			// Load user details and generate JWT
			final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
			final String token = jwtUtil.generateToken(userDetails);

			// Return the generated token
			return ResponseEntity.ok(token);
		} catch (Exception ex) {
			// Return unauthorized status for authentication failures
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
		}
	}
}