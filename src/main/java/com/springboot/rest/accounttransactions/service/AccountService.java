package com.springboot.rest.accounttransactions.service;

import com.springboot.rest.accounttransactions.controller.model.account.AccountDetails;
import com.springboot.rest.accounttransactions.controller.model.account.AccountDetailsResponse;
import com.springboot.rest.accounttransactions.controller.model.account.AccountTransactions;
import com.springboot.rest.accounttransactions.controller.model.account.TransactionDetailsResponse;
import com.springboot.rest.accounttransactions.entity.Account;
import com.springboot.rest.accounttransactions.entity.AccountTransaction;
import com.springboot.rest.accounttransactions.exception.AccountNumberNotFoundException;
import com.springboot.rest.accounttransactions.repository.AccountRepository;
import com.springboot.rest.accounttransactions.repository.AccountTransactionRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AccountService {

  private final AccountRepository accountRepository;
  private final AccountTransactionRepository accountTransactionRepository;

  public AccountDetails getAllAccountDetails() {

    List<Account> accountDetailsList = accountRepository.findAll();
    List<AccountDetailsResponse> accountDetailsResponseList = accountDetailsList.stream()
        .map(this::buildAccountDetailsResponse)
        .collect(Collectors.toList());

    return AccountDetails.builder()
        .accountDetailsResponse(accountDetailsResponseList)
        .build();
  }

  public AccountTransactions getAccountTransactions(@NonNull final String accountNumber) {

    accountRepository.findById(accountNumber)
        .orElseThrow(() -> new AccountNumberNotFoundException(
            "Could not find Account Number: " + accountNumber));

    List<AccountTransaction> accountTransactionsList = accountTransactionRepository.findByAccountNumber(accountNumber);
    List<TransactionDetailsResponse> transactionDetailsResponseList = accountTransactionsList.stream()
        .map(accountTransaction -> buildAccountTransactionResponse(accountTransaction, accountNumber))
        .collect(Collectors.toList());

    return AccountTransactions.builder()
        .transactionDetailsResponses(transactionDetailsResponseList)
        .build();
  }

  private AccountDetailsResponse buildAccountDetailsResponse(@NonNull final Account account) {
    return AccountDetailsResponse
        .builder()
        .accountNumber(account.getAccountNumber())
        .accountName(account.getAccountName())
        .accountType(account.getAccountType().name())
        .balanceDate(account.getBalanceDate())
        .currency(account.getCurrency())
        .openingBalance(account.getOpeningBalance())
        .build();
  }

  private TransactionDetailsResponse buildAccountTransactionResponse(@NonNull final AccountTransaction accountTransaction, @NonNull final String accountNumber) {
    return TransactionDetailsResponse
        .builder()
        .accountNumber(accountNumber)
        .accountName(accountTransaction.getAccountName())
        .creditAmount(accountTransaction.getCreditAmount())
        .debitAmount(accountTransaction.getDebitAmount())
        .isDebitCredit(accountTransaction.getIsCreditDebit())
        .valueDate(accountTransaction.getValueDate())
        .transactionRemarks(accountTransaction.getTransactionRemarks())
        .currency(accountTransaction.getCurrency())
        .build();
  }

}
