package com.fdmgroup.BankingApplication.jwtsecurity;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@WebFilter("/api/*")
public class Jwtfilter extends OncePerRequestFilter {

	private JwtUtil jwtutil;
	private CustomUserDetailsService customUserDetailService;

	@Autowired
	public Jwtfilter(JwtUtil jwtutil, CustomUserDetailsService customeUserDetailService) {
		super();
		this.jwtutil = jwtutil;
		this.customUserDetailService = customeUserDetailService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String path = request.getRequestURI();
		if (path.startsWith("/auth/") || path.startsWith("/h2-console/")) {
			filterChain.doFilter(request, response);
			return;
		}
		String bearerToken = request.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			bearerToken = bearerToken.substring(7);
			if (jwtutil.validateToken(bearerToken)) {
				String username = jwtutil.extractUsername(bearerToken);
				UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);
	}
}