package com.fdmgroup.BankingApplication.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fdmgroup.BankingApplication.customer.Customer;

public interface AccountRepository extends JpaRepository<Account, Long> {

	@Query("SELECT a FROM Account a JOIN a.customer c JOIN c.address addr WHERE addr.city = :city")

	List<Account> findAccountsByCity(@Param("city") String city);
}