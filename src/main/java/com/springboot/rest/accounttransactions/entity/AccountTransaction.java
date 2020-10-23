package com.springboot.rest.accounttransactions.entity;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "account_transaction")
@Data
@ToString(exclude = {"account"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountTransaction {

  @Id
  @GeneratedValue
  private Long id;
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "account_number", nullable = false)
  private Account account;

  private String accountName;

  private ZonedDateTime valueDate;
  private String currency;
  private BigDecimal debitAmount;
  private BigDecimal creditAmount;
  private String isCreditDebit;
  private String transactionRemarks;

}
