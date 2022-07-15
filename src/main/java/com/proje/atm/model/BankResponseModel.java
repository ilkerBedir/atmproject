package com.proje.atm.model;

import lombok.Data;

import java.util.List;

@Data
public class BankResponseModel {
  private Long id;
  private String name;
  private List<Long> customers;
}
