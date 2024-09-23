package com.assignment.usertransaction.entity;

import java.math.BigInteger;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transactions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer id;

	@Column
	public BigInteger amount;
	
	@Column
	public Timestamp datetime;
	
	@Column
	public String type;
	
	@ManyToOne
    @JoinColumn(name="account", nullable=false)
    private Accounts accounts;
	
	@Column
	private Boolean status;

}
