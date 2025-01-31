package com.fdmgroup.BankingApplication.jwtsecurity;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.fdmgroup.BankingApplication.employee.EmployeeRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final EmployeeRepository employeeRepository;

	public CustomUserDetailsService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if ("manager".equals(username)) {
			return User.builder().username("manager").password(new BCryptPasswordEncoder().encode("password"))
					.roles("MANAGER").build();
		} else if ("teller".equals(username)) {
			return User.builder().username("teller").password(new BCryptPasswordEncoder().encode("password"))
					.roles("TELLER").build();
		} else {
			throw new UsernameNotFoundException("User not found");
		}
	}
}