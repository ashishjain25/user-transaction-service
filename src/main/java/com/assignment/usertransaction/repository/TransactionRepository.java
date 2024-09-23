package com.assignment.usertransaction.repository;

import java.sql.Timestamp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.usertransaction.entity.Transactions;

public interface TransactionRepository extends JpaRepository<Transactions, Integer> {

	Page<Transactions> findAllByTypeAndDatetimeBetween(String type, Timestamp datetimeStart, Timestamp datetimeEnd,
			Pageable pageable);
	
	
	
}
