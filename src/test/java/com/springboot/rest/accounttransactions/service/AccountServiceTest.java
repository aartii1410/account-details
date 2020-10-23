package com.springboot.rest.accounttransactions.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.springboot.rest.accounttransactions.controller.model.account.AccountDetails;
import com.springboot.rest.accounttransactions.controller.model.account.AccountTransactions;
import com.springboot.rest.accounttransactions.entity.Account;
import com.springboot.rest.accounttransactions.entity.AccountTransaction;
import com.springboot.rest.accounttransactions.entity.AccountType;
import com.springboot.rest.accounttransactions.exception.AccountNumberNotFoundException;
import com.springboot.rest.accounttransactions.repository.AccountRepository;
import com.springboot.rest.accounttransactions.repository.AccountTransactionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

  private static final String ACCOUNT_NUMBER = "123456";

  @Mock
  AccountRepository accountRepository;

  @Mock
  AccountTransactionRepository accountTransactionRepository;

  @InjectMocks
  AccountService unit;

  @Test
  public void getAccountDetails_shouldReturnAccountDetails_whenDetailsArePresent() {

    //given
    Account account = mock(Account.class);

    List<Account> accountList = new ArrayList<>();
    accountList.add(account);

    when(accountRepository.findAll()).thenReturn(accountList);
    when(account.getAccountType()).thenReturn(AccountType.SAVINGS);

    //when
    AccountDetails accountDetails = unit.getAllAccountDetails();

    //then
    assertThat(accountDetails.getAccountDetailsResponse()).hasSize(1);
  }

  @Test
  public void getAccountTransactions_shouldThrowAccountNumberNotFoundException_whenAccountNumberIsNotFound() {
    //given
    when(accountRepository.findById(ACCOUNT_NUMBER)).thenReturn(Optional.empty());

    Throwable actual = catchThrowable(
        () -> unit.getAccountTransactions(ACCOUNT_NUMBER));

    //when
    assertThat(actual).isInstanceOf(AccountNumberNotFoundException.class)
        .hasMessage("Could not find Account Number: " + ACCOUNT_NUMBER);
  }

  @Test
  public void getAccountDetails_shouldReturnAnEmptyList_whenAccountDetailsAreNotFound() {

    //when
    AccountDetails accountDetails = unit.getAllAccountDetails();

    //then
    assertThat(accountDetails.getAccountDetailsResponse()).isEmpty();
  }

  @Test
  public void getAccountTransactions_shouldReturnAccountTransactions_whenAccountNumberIsFound() {

    Account account = mock(Account.class);
    AccountTransaction accountTransaction = mock(AccountTransaction.class);
    when(accountRepository.findById(ACCOUNT_NUMBER)).thenReturn(Optional.of(account));
    when(accountTransactionRepository.findByAccountNumber(ACCOUNT_NUMBER)).thenReturn(List.of(accountTransaction));
    //when
    AccountTransactions accountTransactions = unit.getAccountTransactions(ACCOUNT_NUMBER);

    //then
    assertThat(accountTransactions).isNotNull();
  }

}
