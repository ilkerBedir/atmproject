package com.proje.atm.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class WithdrawMoneyRequestModel {
  @NotNull
  private Long customerID;
  @Min(0)
  private double money;
}
