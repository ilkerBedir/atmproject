package com.proje.atm.entity;

import com.proje.atm.model.BankResponseModel;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "bank")
public class BankEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "lastAccountNumber")
  private String lastAccountNumber;

  @Column(name = "customers")
  @OneToMany(mappedBy = "id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<CustomerEntity> customers;

  public BankResponseModel convertModel() {
    BankResponseModel bankResponseModel = new BankResponseModel();
    bankResponseModel.setId(this.id);
    bankResponseModel.setName(this.name);
    bankResponseModel.setCustomers(this.customers.stream().map(customers -> customers.getId()).collect(Collectors.toList()));
    return bankResponseModel;
  }
}
