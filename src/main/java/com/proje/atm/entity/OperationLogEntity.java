package com.proje.atm.entity;

import com.proje.atm.enums.OperationType;
import lombok.Data;
import org.joda.time.LocalDate;

import javax.persistence.*;

@Data
@Entity
@Table(name = "operationLogs")
public class OperationLogEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private int id;
  @Column(name = "description")
  private String description;

  @Column(name = "operation_type")
  private OperationType operationType;

  @Column(name="created_date")
  private LocalDate date;

  @ManyToOne
  @JoinColumn(name="customer")
  private CustomerEntity customer;

}
