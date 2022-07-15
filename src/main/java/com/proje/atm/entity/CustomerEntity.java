package com.proje.atm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proje.atm.model.CustomerResponseModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "customer")
public class CustomerEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "accountNumber", nullable = false, unique = true)
  private String accountNumber;

  @Column(name = "createdDate")
  private String createdDate;

  @Column(name = "balance", nullable = false)
  @Min(value = 0, message = "bakiye 0'dan küçük olamaz.")
  private double balance;

  @JsonIgnore(value = true)
  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "failed_login_count", nullable = false)
  private int failedLoginCount;

  @ManyToOne
  @JoinColumn(name = "bank")
  private BankEntity bank;

  public CustomerResponseModel convertModel() {
    CustomerResponseModel customerResponseModel = new CustomerResponseModel();
    customerResponseModel.setId(this.id);
    customerResponseModel.setName(this.name);
    customerResponseModel.setCreatedDate(this.createdDate);
    customerResponseModel.setAccountNumber(this.accountNumber);
    customerResponseModel.setBalance(this.balance);
    customerResponseModel.setPassword(this.password);
    customerResponseModel.setFailedLoginCount(this.failedLoginCount);
    customerResponseModel.setBank(this.bank.getId());
    return customerResponseModel;
  }
}
