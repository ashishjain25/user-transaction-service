package com.assignment.usertransaction.validation;

import java.math.BigInteger;
import com.assignment.usertransaction.record.TransactionRecord;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AmountValidator implements ConstraintValidator<AmountAnnotation, TransactionRecord> {

	
	@Override
	public boolean isValid(TransactionRecord transactionRecord, ConstraintValidatorContext context) {
		if (StringUtils.isNotBlank(transactionRecord.type()) && transactionRecord.type().equals("out")
				&& transactionRecord.amount().compareTo(BigInteger.valueOf(1000000)) > 0) {
			// Withdrawal balance is more than permissible limit of $10,0000
			return false;

		}

		return true;
	}
}
