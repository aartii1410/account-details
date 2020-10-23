package com.springboot.rest.accounttransactions.controller.model.account;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDetails {

  private List<AccountDetailsResponse> accountDetailsResponse;

}
