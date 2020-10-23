package com.springboot.rest.accounttransactions.entity;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "account")
@ToString(exclude = {"accountTransaction"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

  @Id
  private String accountNumber;
  @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<AccountTransaction> accountTransaction;
  private String accountName;
  @Enumerated(value = EnumType.STRING)
  private AccountType accountType;
  private ZonedDateTime balanceDate;
  private String currency;
  private BigDecimal openingBalance;


}
