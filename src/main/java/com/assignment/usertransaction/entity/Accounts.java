package com.assignment.usertransaction.entity;


import java.math.BigInteger;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Accounts {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer id;

	@Column
	public BigInteger balance;
	
	@Column
	public Integer accountno;
	
	@Column
	public String username;
	
	@Column
	public Long mobile;
	
	@Column
	public String email;

	@Column
	public Boolean active;
	
	
}
