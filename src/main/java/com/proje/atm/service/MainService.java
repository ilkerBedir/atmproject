package com.proje.atm.service;

import com.proje.atm.entity.BankEntity;
import com.proje.atm.entity.CustomerEntity;
import com.proje.atm.model.CustomerResponseModel;
import com.proje.atm.model.LoginRequestModel;
import com.proje.atm.model.RegisterRequestModel;
import com.proje.atm.repository.BankRepository;
import com.proje.atm.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MainService {

  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  BankRepository bankRepository;

  @Autowired
  PasswordService passwordService;

  public CustomerResponseModel login(LoginRequestModel requestModel) {
    log.trace("login yapıldı");
    CustomerEntity customerEntity = customerRepository.findByAccountNumber(requestModel.getAccountNumber()).get();
    if (passwordService.verifyPassword(customerEntity.getPassword(), requestModel.getPassword())) {
      log.trace("giris basarılı");
      return customerEntity.convertModel();
    }
    customerEntity.setFailedLoginCount(customerEntity.getFailedLoginCount()+1);
    customerRepository.save(customerEntity);
    throw new IllegalStateException("giris basarisiz");
  }

  public CustomerResponseModel register(RegisterRequestModel requestModel) {
    log.trace("register yapıldı");
    requestModel.getBankID();
    BankEntity bank = bankRepository.findById(requestModel.getBankID()).get();
    int accountNumber = Integer.parseInt(bank.getLastAccountNumber()) + 1;
    String accountNumberStr = String.format("%07d", accountNumber);
    String encodedPassword = passwordService.passwordEncrypt(requestModel.getPassword());
    CustomerEntity customer = CustomerEntity.builder()
      .name(requestModel.getName())
      .balance(requestModel.getBalance())
      .bank(bank)
      .createdDate(LocalDate.now().toString())
      .failedLoginCount(0)
      .accountNumber(accountNumberStr)
      .password(encodedPassword)
      .build();
    bank.setLastAccountNumber(accountNumberStr);
    bankRepository.save(bank);
    customerRepository.save(customer);
    return customer.convertModel();
  }

}
