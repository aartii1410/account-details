package com.springboot.rest.accounttransactions.controller.model.validation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto {

  private String field;
  private String message;
}
