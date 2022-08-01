package com.proje.atm.controller;

import com.proje.atm.entity.BankEntity;
import com.proje.atm.entity.CustomerEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerTest {

  private CustomerEntity customer;
  @Autowired
  MockMvc mockMvc;

  @MockBean
  AdminController adminController;

  @BeforeEach
  public void setUp() {
    customer = CustomerEntity.builder().build();
    BankEntity bankEntity = new BankEntity();
    bankEntity.setId(3l);
    customer.setBank(bankEntity);
  }

  @Test
  void getCustomerByID() throws Exception {
    when(adminController.getCustomerByID(3, 3)).thenReturn(customer.convertModel());
    this.mockMvc.perform(get("/bank/customerByID?bankID=3&customerID=3"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.bank", is(3)));
    verify(adminController).getCustomerByID(3, 3);
  }

  @Test
  void getCustomers() throws Exception {
    when(adminController.getCustomers(3)).thenReturn(List.of(customer.convertModel()));
    this.mockMvc.perform(get("/bank/customers?bankID=3"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$[0].bank", is(3)));
    verify(adminController).getCustomers(3);
  }
}