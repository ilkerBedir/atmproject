package com.proje.atm.model;

import lombok.Data;

@Data
public class CustomerResponseModel {
  private Long id;
  private String name;
  private String accountNumber;
  private String createdDate;
  private double balance;
  private String password;
  private int failedLoginCount;
  private long bank;
}
