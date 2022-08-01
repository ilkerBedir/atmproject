package com.proje.atm.service;

import com.proje.atm.model.CustomerResponseModel;
import com.proje.atm.model.LoginRequestModel;
import com.proje.atm.model.RegisterRequestModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MainServiceTest {

  @Autowired
  MainService mainService;

  @Test
  void login() {
    LoginRequestModel request1 = LoginRequestModel.builder().accountNumber("0000001").password("12345").build();
    LoginRequestModel request2 = LoginRequestModel.builder().accountNumber("0000001").password("123456").build();
    assertEquals(1l,mainService.login(request1).getId());
    assertThrows(IllegalStateException.class,()-> mainService.login(request2));
  }

  @Test
  void register() {
    RegisterRequestModel request = RegisterRequestModel.builder().name("rekli").balance(100.0).password("Test12345").bankID(1l).build();
    CustomerResponseModel registeredCustomer = mainService.register(request);
    assertNotNull(mainService.customerRepository.findByAccountNumber(registeredCustomer.getAccountNumber()));
  }
}