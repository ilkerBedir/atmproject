package com.proje.atm.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
@Builder
public class AddMoneyRequestModel {
  @NotNull(message = "müşteri boş olamaz")
  private Long customerID;
  @PositiveOrZero()
  private double money;
}
