package com.proje.atm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proje.atm.entity.BankEntity;
import com.proje.atm.entity.CustomerEntity;
import com.proje.atm.model.AddMoneyRequestModel;
import com.proje.atm.model.TransferMoneyRequestModel;
import com.proje.atm.model.WithdrawMoneyRequestModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

  private CustomerEntity customer;
  private ObjectMapper objectMapper;
  private static Map<String, Object> addMoneyBody;
  private static Map<String, Object> withDrawMoneyBody;
  private static Map<String, Object> transferMoneyBody;
  @Autowired
  MockMvc mockMvc;

  @MockBean
  CustomerController customerController;

  @BeforeEach
  void setUp() {
    customer = CustomerEntity.builder().build();
    BankEntity bankEntity = new BankEntity();
    bankEntity.setId(3l);
    customer.setBank(bankEntity);

    objectMapper = new ObjectMapper();

    addMoneyBody = new HashMap<>();
    addMoneyBody.put("customerID", 5l);
    addMoneyBody.put("money", 100.0);


    withDrawMoneyBody = new HashMap<>();
    withDrawMoneyBody.put("customerID", 5l);
    withDrawMoneyBody.put("money", 100.0);

    transferMoneyBody = new HashMap<>();
    transferMoneyBody.put("sourceCustomerID", 5l);
    transferMoneyBody.put("money", 100.0);
    transferMoneyBody.put("destCustomerAccountNumber", "1234567");
  }

  @Test
  void addMoney() throws Exception {
    AddMoneyRequestModel request = AddMoneyRequestModel.builder().customerID(5l).money(100.0).build();
    when(customerController.addMoney(request)).thenReturn(customer.convertModel());

    this.mockMvc.perform(post("/customer/add-money")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(addMoneyBody))
      )
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.bank", is(3)));
    verify(customerController).addMoney(request);
  }

  @Test
  void transferMoney() throws Exception {
    TransferMoneyRequestModel request = TransferMoneyRequestModel.builder().sourceCustomerID(5l).money(100.0).destCustomerAccountNumber("1234567").build();
    when(customerController.transferMoney(request)).thenReturn(HttpStatus.OK);
    this.mockMvc.perform(post("/customer/transfer-money")
      .contentType(MediaType.APPLICATION_JSON)
      .content(objectMapper.writeValueAsString(transferMoneyBody))
    ).andExpect(status().isOk());
    verify(customerController).transferMoney(request);
  }

  @Test
  void withdrawMoney() throws Exception {
    WithdrawMoneyRequestModel request = WithdrawMoneyRequestModel.builder().customerID(5l).money(100.0).build();
    when(customerController.withdrawMoney(request)).thenReturn(HttpStatus.OK);
    this.mockMvc.perform(post("/customer/withdraw-money")
      .contentType(MediaType.APPLICATION_JSON)
      .content(objectMapper.writeValueAsString(withDrawMoneyBody))
    ).andExpect(status().isOk());
    verify(customerController).withdrawMoney(request);
  }
}