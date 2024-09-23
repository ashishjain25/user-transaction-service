package com.assignment.usertransaction.service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.assignment.usertransaction.entity.Accounts;
import com.assignment.usertransaction.entity.Transactions;
import com.assignment.usertransaction.exception.TransactionException;
import com.assignment.usertransaction.record.PaginatedTransactionRecord;
import com.assignment.usertransaction.record.TransactionRecord;
import com.assignment.usertransaction.repository.AccountsRepository;
import com.assignment.usertransaction.repository.TransactionRepository;
import jakarta.transaction.Transactional;

@Service
public class TransactionService {

	static Logger logger = LoggerFactory.getLogger(TransactionService.class);

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private AccountsRepository accountsRepository;

	private boolean isIntegerNotNull(Integer num) {
		return Optional.ofNullable(num).orElse(0) == 0;
	}

	@Transactional
	public Transactions saveTransaction(TransactionRecord transaction, Integer accountId) {

		Accounts accounts = accountsRepository.findByAccountno(accountId);

		verifyBalancePostWithdrawl(accounts, transaction.amount());

		Transactions savedTransaction = transactionRepository
				.save(mapTransactionEntity(transaction, accountId, accounts));

		if (transaction.type().equalsIgnoreCase("IN")) {
			accounts.setBalance(accounts.getBalance().add(transaction.amount()));
		}

		if (transaction.type().equalsIgnoreCase("OUT")) {
			accounts.setBalance(accounts.getBalance().subtract(transaction.amount()));
		}

		Accounts updatedAccounts = accountsRepository.save(accounts);

		if (!ObjectUtils.isEmpty(updatedAccounts)) {
			savedTransaction.setStatus(Boolean.TRUE);
			savedTransaction = transactionRepository.save(savedTransaction);
		}

		return savedTransaction;
	}

	public Page<Transactions> getTransactions(int page, int size, PaginatedTransactionRecord transactionRecord) {
		
		Timestamp fromDate = Timestamp.valueOf(transactionRecord.fromDate());
		Timestamp toDate = Timestamp.valueOf(transactionRecord.toDate());
	
		return transactionRepository.findAllByTypeAndDatetimeBetween(transactionRecord.type(),
				fromDate, toDate, PageRequest.of(page, size));
	}

	private boolean verifyBalancePostWithdrawl(Accounts accounts, BigInteger withdrawlAmount) {
		if ((accounts.getBalance().subtract(withdrawlAmount)).compareTo(BigInteger.ZERO) <= 0) {
			throw new TransactionException(
					"Post withdrwal balance amount will be zero or less than 0. Please check the amount in transaction.");
		}

		return true;

	}

	private Transactions mapTransactionEntity(TransactionRecord transactionRecord, Integer accountId,
			Accounts accounts) {
		Transactions transactionsEntity = new Transactions();
		transactionsEntity.setAmount(transactionRecord.amount());
		transactionsEntity.setType(transactionRecord.type());
		transactionsEntity.setDatetime(Timestamp.from(Instant.now()));
		transactionsEntity.setAccounts(accounts);
		return transactionsEntity;
	}
}
