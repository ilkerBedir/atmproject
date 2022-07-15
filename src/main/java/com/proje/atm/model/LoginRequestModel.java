package com.proje.atm.model;

import lombok.Data;

@Data
public class LoginRequestModel {
  private String accountNumber;
  private String password;
}
