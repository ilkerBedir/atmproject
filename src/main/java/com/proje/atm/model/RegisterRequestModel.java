package com.proje.atm.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RegisterRequestModel {
  @NotNull
  @NotEmpty
  private String name;

  @Min(0)
  private double balance;
  private Long bankID;

  private String password;
}
