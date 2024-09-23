DROP SCHEMA IF EXISTS usertransaction;

CREATE SCHEMA usertransaction;

USE usertransaction;

CREATE TABLE accounts(
  id INTEGER PRIMARY KEY,
  accountno BIGINT NOT NULL,
  username VARCHAR(255) NOT NULL,
  mobile INTEGER NOT NULL,
  email VARCHAR(255) NOT NULL,
  balance BIGINT NOT NULL,
  active boolean NOT NULL
);

CREATE TABLE transactions(
  id INTEGER PRIMARY KEY,
  amount BIGINT NOT NULL,
  executeddate datetime NOT NULL,
  type VARCHAR(10) NOT NULL,
  account INTEGER NOT NULL,
  status BOOLEAN,
  FOREIGN KEY (account) references accounts(id)
);