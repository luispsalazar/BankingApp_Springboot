package com.fdmgroup.BankingApplication.jwtsecurity;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;

@Component
public class JwtUtil {
	@Autowired
	private CustomUserDetailsService customUserDetailService;

	private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(
			"secret_key_j984504fad3334e9kjfkasdj9485904lkf09409580395mfaskfsakf".getBytes(StandardCharsets.UTF_8));

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		System.out.println("Validation key: " + SECRET_KEY);
		return Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).getPayload();
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public String generateToken(UserDetails userDetails) {
		String token = Jwts.builder().setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 5))
				.signWith(SECRET_KEY, SignatureAlgorithm.HS256).compact();

		System.out.println("Generated Token: " + token);
		System.out.println(
				"Encoded SECRET_KEY (generation): " + Base64.getEncoder().encodeToString(SECRET_KEY.getEncoded()));
		return token;
	}

	public boolean validateToken(String token) {
		try {
			final String extractedUsername = extractUsername(token);
			return extractedUsername != null && !isTokenExpired(token);
		} catch (JwtException | IllegalArgumentException e) {
			System.err.println("Token validation failed: " + e.getMessage());
			return false;
		}
	}
}