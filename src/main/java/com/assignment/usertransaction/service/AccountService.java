package com.assignment.usertransaction.service;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.assignment.usertransaction.entity.Accounts;
import com.assignment.usertransaction.record.AccountRecord;
import com.assignment.usertransaction.record.UpdateAccountRecord;
import com.assignment.usertransaction.repository.AccountsRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;

@Service
public class AccountService {
	
	@Autowired
	private AccountsRepository accountsRepository;
	
	@Transactional
	public Accounts saveAccount(AccountRecord account) {
		return accountsRepository.save(mapAccountEntity(account));
	}
	
	@Transactional
	public Accounts disableAccount(Integer accountNo) {
		Accounts account = accountsRepository.findByAccountno(accountNo);
		account.setActive(Boolean.FALSE);
		return accountsRepository.save(account);
	}
	
	
	@Transactional
	public Accounts getAccount(Integer accountNo) {
		return accountsRepository.findByAccountno(accountNo);
	}
	
	@Transactional
	public Accounts updateAccount(UpdateAccountRecord account, Integer accountNo) {
		Accounts accounts = accountsRepository.findByAccountno(accountNo);
		
		if(StringUtils.isNotBlank(account.username())) accounts.setUsername(account.username());
		
		if(isBigIntegerNotNull(account.balance())) accounts.setBalance(account.balance());
		
		if(StringUtils.isNotBlank(account.email())) accounts.setEmail(account.email());
		
		if(account.mobile() != null) accounts.setMobile(account.mobile());
		
		return accountsRepository.save(accounts);
	}
	
	
	private boolean isBigIntegerNotNull(BigInteger num) {
	    if(num != null && num.signum() == 1) return true;
	    return false;
	}
	
	private Accounts mapAccountEntity(AccountRecord account) {
		Accounts accountsEntity = new Accounts();
		
		accountsEntity.setAccountno(getAccountNo());
		accountsEntity.setBalance(account.balance());
		accountsEntity.setEmail(account.email());
		accountsEntity.setMobile(account.mobile());
		accountsEntity.setActive(Boolean.TRUE);
		accountsEntity.setUsername(account.username());
		
		return accountsEntity;
	}
	
	private Integer getAccountNo(){
		Accounts account = accountsRepository.findFirstByOrderByIdDesc();
		if(account !=  null) {
			return new AtomicInteger(account.getAccountno()).incrementAndGet();
		}else {
			return new AtomicInteger().incrementAndGet();
		}
	}
	
	

}
