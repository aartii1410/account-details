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
public class AccountDetailsResponse {

  private String accountNumber;
  private String accountName;
  private String accountType;
  private ZonedDateTime balanceDate;
  private String currency;
  private BigDecimal openingBalance;

}
