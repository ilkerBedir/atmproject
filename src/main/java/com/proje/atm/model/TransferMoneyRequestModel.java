package com.proje.atm.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class TransferMoneyRequestModel {
  @NotNull
  private Long sourceCustomerID;
  @Min(0)
  private double money;
  @NotNull
  private String destCustomerAccountNumber;
}
