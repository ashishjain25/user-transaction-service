package com.assignment.usertransaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport(
        pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO
)
public class UserTransactionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserTransactionServiceApplication.class, args);
	}

}
