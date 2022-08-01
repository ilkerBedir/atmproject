package com.proje.atm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proje.atm.entity.BankEntity;
import com.proje.atm.entity.CustomerEntity;
import com.proje.atm.model.LoginRequestModel;
import com.proje.atm.model.RegisterRequestModel;
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
class MainControllerTest {
  private CustomerEntity customer;

  private static Map<String, Object> loginBody;
  private static Map<String, Object> registerBody;
  private static ObjectMapper objectMapper;

  @Autowired
  MockMvc mockMvc;

  @MockBean
  MainController mainController;

  @BeforeEach
  public void setUp() {
    customer = CustomerEntity.builder().build();
    BankEntity bankEntity = new BankEntity();
    bankEntity.setId(3l);
    customer.setBank(bankEntity);

    loginBody = new HashMap<>();
    loginBody.put("accountNumber", "1234567");
    loginBody.put("password", "test12345");
    objectMapper = new ObjectMapper();

    registerBody = new HashMap<>();
    registerBody.put("name", "test");
    registerBody.put("balance", 1000);
    registerBody.put("bankID", 3l);
    registerBody.put("password", "test12345");
  }

  @Test
  void login() throws Exception {
    LoginRequestModel user = LoginRequestModel.builder().accountNumber("1234567").password("test12345").build();
    when(mainController.login(user)).thenReturn(customer.convertModel());

    this.mockMvc.perform(post("/api/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(loginBody))
      )
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.bank", is(3)));
    verify(mainController).login(user);
  }

  @Test
  void register() throws Exception {
    RegisterRequestModel request = RegisterRequestModel.builder().bankID(3l).balance(1000).password("test12345").name("test").build();
    when(mainController.register(request)).thenReturn(HttpStatus.OK);
    this.mockMvc.perform(post("/api/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(registerBody))
      )
      .andExpect(status().isOk());
    verify(mainController).register(request);

  }
}