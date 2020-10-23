package com.springboot.rest.accounttransactions.component;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.OK;

import com.springboot.rest.accounttransactions.AccountTransactionsApplication;
import com.springboot.rest.accounttransactions.controller.model.account.AccountDetails;
import com.springboot.rest.accounttransactions.controller.model.account.AccountDetailsResponse;
import com.springboot.rest.accounttransactions.controller.model.account.AccountTransactions;
import com.springboot.rest.accounttransactions.controller.model.account.TransactionDetailsResponse;
import com.springboot.rest.accounttransactions.entity.Account;
import com.springboot.rest.accounttransactions.entity.AccountTransaction;
import com.springboot.rest.accounttransactions.entity.AccountType;
import com.springboot.rest.accounttransactions.helper.RestTemplateErrorHandler;
import com.springboot.rest.accounttransactions.repository.AccountRepository;
import com.springboot.rest.accounttransactions.repository.AccountTransactionRepository;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = {AccountTransactionsApplication.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class AccountTransactionsTest {

  private static final String ACCOUNT_TRANSACTIONS_ENDPOINT = "/account-transactions/v1";
  private static final String ACCOUNT_NUMBER = "585309209";
  private static final String ACCOUNT_NUMBER1 = "585309210";
  private static final String ACCOUNT_NAME = "Test";

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private AccountTransactionRepository accountTransactionRepository;

  @LocalServerPort
  protected int randomServerPort;

  @Before
  public void baseSetup() {

    restTemplate = new RestTemplateBuilder().rootUri("http://localhost:" + randomServerPort)
        .errorHandler(new RestTemplateErrorHandler()).build();
  }

  @Test
  public void getAllAccountDetails_shouldReturnAccountDetailsFromDatabase() {

    saveAccountDetails();
    //when
    AccountDetails accountDetails = executeGetAllAccountDetails();
    List<AccountDetailsResponse> accountDetailsResponse = accountDetails.getAccountDetailsResponse();

    List<String> accountNumber = accountDetailsResponse.stream()
        .map(AccountDetailsResponse::getAccountNumber)
        .collect(Collectors.toList());
    //and
    assertThat(accountDetails.getAccountDetailsResponse()).isNotEmpty();
    assertThat(accountNumber).contains(ACCOUNT_NUMBER);
  }

  @Test
  public void getAccountTransactions_shouldReturngetAccountTransactionsFromDatabase() {

    saveAccountTransactions();
    //when
    AccountTransactions accountTransactions = executeGetAllAccountTransactions();
    List<TransactionDetailsResponse> transactionDetailsResponses = accountTransactions.getTransactionDetailsResponses();

    List<String> accountNumber = transactionDetailsResponses.stream()
        .map(TransactionDetailsResponse::getAccountNumber)
        .collect(Collectors.toList());
    //and
    assertThat(accountTransactions.getTransactionDetailsResponses()).isNotEmpty();
    assertThat(accountNumber).contains(ACCOUNT_NUMBER1);
  }

  private AccountDetails executeGetAllAccountDetails() {

    HttpEntity<Object> requestEntity = getObjectHttpEntity();

    ResponseEntity<AccountDetails> accountDetailsResponseEntity = restTemplate
        .exchange(ACCOUNT_TRANSACTIONS_ENDPOINT + "/account-details",
            GET, requestEntity, AccountDetails.class);
    assertThat(accountDetailsResponseEntity.getStatusCode()).isEqualTo(OK);
    return accountDetailsResponseEntity.getBody();
  }

  private HttpEntity<Object> getObjectHttpEntity() {
    MultiValueMap<String, String> headers = new HttpHeaders();
    return new HttpEntity<>(headers);
  }

  private Account saveAccountDetails() {
    Account account = Account.builder()
        .accountNumber(ACCOUNT_NUMBER)
        .accountName(ACCOUNT_NAME)
        .accountType(AccountType.CURRENT)
        .balanceDate(ZonedDateTime.now())
        .currency("AUD")
        .openingBalance(BigDecimal.valueOf(1000))
        .build();
    accountRepository.save(account);

    return account;
  }

  private AccountTransaction saveAccountTransactions() {
    Account account = Account.builder()
        .accountNumber(ACCOUNT_NUMBER1)
        .accountName(ACCOUNT_NAME)
        .accountType(AccountType.CURRENT)
        .balanceDate(ZonedDateTime.now())
        .currency("AUD")
        .openingBalance(BigDecimal.valueOf(1000))
        .build();
    accountRepository.save(account);

    AccountTransaction accountTransaction = AccountTransaction
        .builder()
        .account(account)
        .accountName(ACCOUNT_NAME)
        .creditAmount(BigDecimal.valueOf(100))
        .debitAmount(BigDecimal.valueOf(0))
        .currency("AUD")
        .id(1001L)
        .isCreditDebit("Credit")
        .transactionRemarks("Money Credited")
        .valueDate(ZonedDateTime.now())
        .build();

    accountTransactionRepository.save(accountTransaction);
    return accountTransaction;
  }

  private AccountTransactions executeGetAllAccountTransactions() {

    HttpEntity<Object> requestEntity = getObjectHttpEntity();

    ResponseEntity<AccountTransactions> accountTransactionsResponseEntity = restTemplate
        .exchange(ACCOUNT_TRANSACTIONS_ENDPOINT + "/account-details/" + ACCOUNT_NUMBER1,
            GET, requestEntity, AccountTransactions.class);
    assertThat(accountTransactionsResponseEntity.getStatusCode()).isEqualTo(OK);
    return accountTransactionsResponseEntity.getBody();
  }

}
