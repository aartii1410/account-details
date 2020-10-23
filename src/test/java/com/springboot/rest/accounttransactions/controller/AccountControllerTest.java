package com.springboot.rest.accounttransactions.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.springboot.rest.accounttransactions.controller.model.account.AccountDetails;
import com.springboot.rest.accounttransactions.controller.model.account.AccountTransactions;
import com.springboot.rest.accounttransactions.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {

  @Mock
  AccountService accountService;

  @InjectMocks
  private AccountController unit;

  private static final String ACCOUNT_NUMBER = "585309209";

  @Test
  public void getAllAccountDetails_shouldDelegateToService() {

    //when
    ResponseEntity<AccountDetails> accountDetailsListResponseEntity = unit.getAllAccountDetails();

    //then
    assertThat(accountDetailsListResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    // and
    verify(accountService, times(1)).getAllAccountDetails();
  }

  @Test
  public void getAccountTransactions_shouldDelegateToService() {

    //when
    ResponseEntity<AccountTransactions> accountTransactions = unit.getAccountTransactions(ACCOUNT_NUMBER);

    //then
    assertThat(accountTransactions.getStatusCode()).isEqualTo(HttpStatus.OK);

    // and
    verify(accountService, times(1)).getAccountTransactions(ACCOUNT_NUMBER);
  }

}
