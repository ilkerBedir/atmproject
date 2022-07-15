package com.proje.atm.repository;

import com.proje.atm.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Component
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

  Optional<CustomerEntity> findByAccountNumber(String accountNumber);

  Optional<CustomerEntity> findById(long id);

  CustomerEntity save(CustomerEntity entity);
}
