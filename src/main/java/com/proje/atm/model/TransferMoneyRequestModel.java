package com.proje.atm.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class TransferMoneyRequestModel {
  @NotNull
  private Long sourceCustomerID;
  @Min(0)
  private double money;
  @NotNull
  private Long destCustomerAccountNumber;
}
