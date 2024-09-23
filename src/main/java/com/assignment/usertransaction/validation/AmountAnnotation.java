package com.assignment.usertransaction.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = AmountValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AmountAnnotation {
	
	String message() default "Withdrawal balance is more than permissible limit of $10,0000. Please change the withdrawl amount.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
