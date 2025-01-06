package com.fdmgroup.BankingApplication.customer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Query("select p from Person p")
	List<Customer> findAllPersons();

	@Query("select c from Company c")
	List<Customer> findAllCompanies();
}