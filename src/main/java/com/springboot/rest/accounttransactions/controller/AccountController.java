package com.springboot.rest.accounttransactions.controller;

import com.springboot.rest.accounttransactions.controller.model.account.AccountDetails;
import com.springboot.rest.accounttransactions.controller.model.account.AccountTransactions;
import com.springboot.rest.accounttransactions.service.AccountService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/account-transactions/v1")
public class AccountController {

  private final AccountService accountService;

  @ApiOperation(value = "Get All Account Details", nickname = "getAllAccountDetails")
  @ApiResponse(code = 200, message = "OK", response = AccountDetails.class)
  @GetMapping("/account-details")
  public ResponseEntity<AccountDetails> getAllAccountDetails() {

    AccountDetails accountDetails = accountService.getAllAccountDetails();

    return ResponseEntity.status(HttpStatus.OK).body(accountDetails);
  }

  @ApiOperation(value = "Get Account Transactions", nickname = "getAccountTransactions")
  @ApiResponse(code = 200, message = "OK", response = AccountTransactions.class)
  @GetMapping("/account-details/{accountNumber}")
  public ResponseEntity<AccountTransactions> getAccountTransactions(
      @PathVariable(value = "accountNumber") final String accountNumber) {

    AccountTransactions accountTransactions = accountService.getAccountTransactions(accountNumber);
    return ResponseEntity.status(HttpStatus.OK).body(accountTransactions);
  }

}
