package com.assignment.usertransaction.record;

import java.math.BigInteger;

import com.assignment.usertransaction.validation.AmountAnnotation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@AmountAnnotation
public record TransactionRecord( @NotNull(message = "Please provide valid transaction type 'IN' or 'OUT' in any case")
		@Pattern(regexp = "\\b(out|in)\\b", message = "Please provide valid transaction type 'IN' or 'OUT' in any case") String type,
		@NotNull(message="Please provide valid amount") BigInteger amount) {

}
