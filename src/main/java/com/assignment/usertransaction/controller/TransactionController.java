package com.assignment.usertransaction.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.assignment.usertransaction.entity.Transactions;
import com.assignment.usertransaction.record.PaginatedTransactionRecord;
import com.assignment.usertransaction.record.TransactionRecord;
import com.assignment.usertransaction.service.TransactionService;

import jakarta.validation.Valid;

@RestController
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping("/accounts/{accountId}/transactions")
	public ResponseEntity<Transactions> createTransaction(@Valid @RequestBody TransactionRecord transaction,
			@PathVariable Integer accountId) {

		Transactions savedAccount = transactionService.saveTransaction(transaction, accountId);
		return new ResponseEntity<>(savedAccount, HttpStatus.OK);
	}

	@GetMapping("/accounts/{accountId}/transactions")
	public Page<Transactions> getTransactions(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @PathVariable Integer accountId,
			@Valid @RequestBody PaginatedTransactionRecord transactionRecord) {
		return transactionService.getTransactions(page, size,transactionRecord);
	}

}
