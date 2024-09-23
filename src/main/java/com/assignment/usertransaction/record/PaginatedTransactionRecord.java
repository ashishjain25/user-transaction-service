package com.assignment.usertransaction.record;

import jakarta.validation.constraints.Pattern;

public record PaginatedTransactionRecord(
		@Pattern(regexp = "\\b(out|in)\\b", message = "Please provide valid transaction type 'IN' or 'OUT' in any case") String type,
		String fromDate, String toDate) {

}
