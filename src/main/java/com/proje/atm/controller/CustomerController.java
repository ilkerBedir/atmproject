package com.proje.atm.controller;

import com.proje.atm.model.AddMoneyRequestModel;
import com.proje.atm.model.CustomerResponseModel;
import com.proje.atm.model.TransferMoneyRequestModel;
import com.proje.atm.model.WithdrawMoneyRequestModel;
import com.proje.atm.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
  @Autowired
  CustomerService customerService;

  @PostMapping("/add-money")
  public CustomerResponseModel addMoney(@RequestBody AddMoneyRequestModel requestModel) {
    return customerService.addMoney(requestModel);
  }

  @PostMapping("/transfer-money")
  public HttpStatus transferMoney(@RequestBody TransferMoneyRequestModel requestModel) {
    customerService.transferMoney(requestModel);
    return HttpStatus.OK;
  }

  @PostMapping("/withdraw-money")
  public HttpStatus withdrawMoney(@RequestBody WithdrawMoneyRequestModel requestModel) {
    customerService.withdrawMoney(requestModel);
    return HttpStatus.OK;
  }
}
