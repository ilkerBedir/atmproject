package com.proje.atm.entity;

import com.proje.atm.model.CustomerResponseModel;
import com.proje.atm.repository.CustomerRepository;
import com.proje.atm.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestPropertySource("/application-test.properties")
@SpringBootTest
class CustomerEntityTest {

  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  CustomerService customerService;


  @Test
  void convertModel() {
    List<CustomerEntity> customers = customerRepository.findAll();
    CustomerResponseModel customerResponseModel = customers.get(0).convertModel();
    Long id = customerResponseModel.getId();
    assertNotNull(id);
    CustomerEntity customerEntity = customerRepository.findById(id).get();
    assertEquals(customerEntity.convertModel(), customerResponseModel);
  }
}