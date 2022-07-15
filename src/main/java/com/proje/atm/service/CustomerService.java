package com.proje.atm.service;

import com.proje.atm.entity.CustomerEntity;
import com.proje.atm.model.AddMoneyRequestModel;
import com.proje.atm.model.CustomerResponseModel;
import com.proje.atm.model.TransferMoneyRequestModel;
import com.proje.atm.model.WithdrawMoneyRequestModel;
import com.proje.atm.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor

public class CustomerService {
  @Autowired
  CustomerRepository customerRepository;

  public CustomerResponseModel addMoney(AddMoneyRequestModel requestModel) {
    log.trace("addMoney yapıldı");
    CustomerEntity customer = customerRepository.findById(requestModel.getCustomerID()).get();
    customer.setBalance(requestModel.getMoney() + customer.getBalance());
    customerRepository.save(customer);
    return customer.convertModel();
  }

  public void transferMoney(TransferMoneyRequestModel requestModel) {
    CustomerEntity customer = customerRepository.findById(requestModel.getSourceCustomerID()).get();
    if (customer.getBalance() - requestModel.getMoney() < 0) {
      throw new IllegalStateException("Bakiye Yetersiz");
    }
    customer.setBalance(requestModel.getMoney() - customer.getBalance());
    CustomerEntity destCustomer = customerRepository.findByAccountNumber(String.valueOf(requestModel.getDestCustomerAccountNumber())).get();
    destCustomer.setBalance(requestModel.getMoney()+destCustomer.getBalance());
    customerRepository.save(customer);
    customerRepository.save(destCustomer);
    log.trace("transferMoney yapıldı");
  }

  public CustomerResponseModel withdrawMoney(WithdrawMoneyRequestModel requestModel) {
    CustomerEntity customer = customerRepository.findById(requestModel.getCustomerID()).get();
    if (customer.getBalance() - requestModel.getMoney() < 0) {
      throw new IllegalStateException("Bakiye Yetersiz");
    }
    customer.setBalance(requestModel.getMoney() - customer.getBalance());
    customerRepository.save(customer);
    log.trace("withdrawMoney yapıldı");
    return customer.convertModel();
  }
}
