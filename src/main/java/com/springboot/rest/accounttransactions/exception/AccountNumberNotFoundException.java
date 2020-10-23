package com.springboot.rest.accounttransactions.exception;

public class AccountNumberNotFoundException extends RuntimeException {

  public AccountNumberNotFoundException(String message) {
    super(message);
  }
}
