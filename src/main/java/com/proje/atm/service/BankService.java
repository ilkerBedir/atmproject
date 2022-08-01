package com.proje.atm.service;

import com.proje.atm.entity.CustomerEntity;
import com.proje.atm.model.CustomerResponseModel;
import com.proje.atm.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class BankService {

  @Autowired
  BankRepository repository;

  public CustomerResponseModel getCustomerByID(long customerID, long bankID) {
    log.trace("getCustomerByID yapıldı");
    Stream<CustomerEntity> stream = repository.findById(bankID).get().getCustomers().stream();
    CustomerEntity customerEntity = stream.filter(customer -> customer.getId() == customerID).collect(Collectors.toList()).get(0);
    CustomerResponseModel customerResponseModel = customerEntity.convertModel();
    return customerResponseModel;
  }

  public List<CustomerResponseModel> getCustomers(long bankID) {
    log.trace("getCustomers yapıldı");
    Set<CustomerEntity> customers = repository.findById(bankID).get().getCustomers();
    List<CustomerResponseModel> collect = customers.stream().map(customer -> customer.convertModel()).collect(Collectors.toList());
    return collect;
  }
}
