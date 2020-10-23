package com.springboot.rest.accounttransactions.controller.model.account;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDetailsResponse {

  private String accountNumber;
  private String accountName;
  private ZonedDateTime valueDate;
  private String currency;
  private BigDecimal debitAmount;
  private BigDecimal creditAmount;
  private String isDebitCredit;
  private String transactionRemarks;

}
