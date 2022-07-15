package com.proje.atm.controller;

import com.proje.atm.model.CustomerResponseModel;
import com.proje.atm.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bank")
@RequiredArgsConstructor
public class AdminController {

  @Autowired
  BankService service;

  @GetMapping("/customerByID")
  public CustomerResponseModel getCustomerByID(@RequestParam long bankID, @RequestParam long customerID) {
    return service.getCustomerByID(customerID, bankID);
  }

  @GetMapping("/customers")
  public List<CustomerResponseModel> getCustomers(@RequestParam long bankID) {
    return service.getCustomers(bankID);
  }
}
