package com.proje.atm.service;

import com.proje.atm.entity.CustomerEntity;
import com.proje.atm.model.AddMoneyRequestModel;
import com.proje.atm.model.CustomerResponseModel;
import com.proje.atm.model.TransferMoneyRequestModel;
import com.proje.atm.model.WithdrawMoneyRequestModel;
import com.proje.atm.repository.CustomerRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerServiceTest {

  private CustomerEntity customerEntity;

  @Autowired
  CustomerService customerService;

  @Autowired
  CustomerRepository customerRepository;

  @BeforeEach
  public void setUp() {
    customerEntity = customerRepository.findById(3l).get();
  }

  @Test
  @Order(1)
  void addMoney() {
    AddMoneyRequestModel requestModel1 = AddMoneyRequestModel.builder().customerID(customerEntity.getId()).money(100.0).build();
    double oldBalance = customerEntity.getBalance();
    CustomerResponseModel response = customerService.addMoney(requestModel1);
    assertEquals(oldBalance + 100.0, response.getBalance());
  }

  @Test
  @Order(3)
  void transferMoney() {
    CustomerResponseModel destCustomer = customerRepository.findByAccountNumber("0000001").get().convertModel();
    TransferMoneyRequestModel request = TransferMoneyRequestModel.builder().sourceCustomerID(customerEntity.getId()).money(50.0).destCustomerAccountNumber(destCustomer.getAccountNumber()).build();
    double sourceOldBalance = customerEntity.getBalance();
    double destCustomerBalance = destCustomer.getBalance();
    customerService.transferMoney(request);
    CustomerEntity sourceCustomer = customerRepository.findById(this.customerEntity.getId()).orElseThrow();
    CustomerEntity destCust = customerRepository.findById(destCustomer.getId()).orElseThrow();
    assertEquals(sourceOldBalance - 50, sourceCustomer.getBalance());
    assertEquals(destCustomerBalance + 50, destCust.getBalance());
  }

  @Test
  @Order(2)
  void withdrawMoney() {
    WithdrawMoneyRequestModel request = WithdrawMoneyRequestModel.builder().customerID(customerEntity.getId()).money(50.0).build();
    double oldBalance = customerEntity.getBalance();
    CustomerResponseModel response = customerService.withdrawMoney(request);
    assertEquals(oldBalance - 50.0, response.getBalance());
  }
}