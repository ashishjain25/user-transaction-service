package com.assignment.usertransaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.assignment.usertransaction.entity.Accounts;

public interface AccountsRepository extends JpaRepository<Accounts, Integer>{
	
	Accounts findByAccountno(Integer accountno);
	Accounts findFirstByOrderByIdDesc();

}
