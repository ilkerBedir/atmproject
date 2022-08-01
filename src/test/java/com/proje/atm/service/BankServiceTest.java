package com.proje.atm.service;

import com.proje.atm.entity.BankEntity;
import com.proje.atm.model.CustomerResponseModel;
import com.proje.atm.repository.BankRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestPropertySource("/application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class BankServiceTest {

  @Autowired
  BankService service;

  @Autowired
  BankRepository repository;

  @Test
  @DisplayName("CustomerTests")
  void getCustomerByID() {
    Long bankId = repository.findAll().get(0).getId();
    Long customerID = service.getCustomers(bankId).get(0).getId();
    CustomerResponseModel customer = service.getCustomerByID(customerID, bankId);
    assertNotNull(customer);
  }

  @Test
  @Order(1)
  void getCustomers() {
    List<BankEntity> banks = repository.findAll();
    assertNotEquals(0,banks.size());
    List<CustomerResponseModel> customers = service.getCustomers(banks.get(0).getId());
    assertNotNull(customers);
  }
}