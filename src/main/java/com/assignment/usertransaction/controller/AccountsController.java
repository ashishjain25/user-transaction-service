package com.assignment.usertransaction.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.usertransaction.entity.Accounts;
import com.assignment.usertransaction.record.AccountRecord;
import com.assignment.usertransaction.record.UpdateAccountRecord;
import com.assignment.usertransaction.service.AccountService;

import jakarta.validation.Valid;

@RestController
public class AccountsController {

	@Autowired
	private AccountService accountService;

	@PostMapping("/accounts")
	public ResponseEntity<Accounts> addAccount(@Valid @RequestBody AccountRecord account) {
		Accounts savedAccount = accountService.saveAccount(account);
		return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
	}

	@GetMapping("/accounts/{accountId}")
	public ResponseEntity<Accounts> getAccountDetails(@PathVariable Integer accountId) {
		Accounts account = accountService.getAccount(accountId);

		if (account == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(account, HttpStatus.OK);
	}

	@DeleteMapping("/accounts/{accountId}")
	public ResponseEntity<Accounts> disableAccount(@PathVariable Integer accountId) {
		Accounts disabledAccount = accountService.disableAccount(accountId);
		return new ResponseEntity<>(disabledAccount, HttpStatus.OK);
	}

	@PutMapping("/accounts/{accountId}")
	public ResponseEntity<Accounts> updateAccount(@Valid @RequestBody UpdateAccountRecord account,
			@PathVariable Integer accountId) {
		Accounts savedAccount = accountService.updateAccount(account,accountId);
		return new ResponseEntity<>(savedAccount, HttpStatus.OK);
	}

	/*
	 * @ResponseStatus(HttpStatus.BAD_REQUEST)
	 * 
	 * @ExceptionHandler(MethodArgumentNotValidException.class) public Map<String,
	 * String> handleValidationExceptions(MethodArgumentNotValidException ex) {
	 * Map<String, String> errors = new HashMap<>();
	 * ex.getBindingResult().getAllErrors().forEach((error) -> { String fieldName =
	 * ((FieldError) error).getField(); String errorMessage =
	 * error.getDefaultMessage(); errors.put(fieldName, errorMessage); }); return
	 * errors; }
	 */

}
