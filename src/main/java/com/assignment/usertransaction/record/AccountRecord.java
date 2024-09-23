package com.assignment.usertransaction.record;

import java.math.BigInteger;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;

public record AccountRecord(Integer accountId, String username,
		@Digits(message = "Number should contain 10 digits.", fraction = 0, integer = 10) Long mobile,
		@Email(message = "Enter valid Email Id.") String email, BigInteger balance) {
}
