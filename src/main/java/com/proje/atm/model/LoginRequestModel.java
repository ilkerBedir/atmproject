package com.proje.atm.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginRequestModel {
  private String accountNumber;
  private String password;
}
