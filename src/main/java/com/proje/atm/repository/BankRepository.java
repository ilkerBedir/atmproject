package com.proje.atm.repository;

import com.proje.atm.entity.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<BankEntity, Long> {
  Optional<BankEntity> findById(Long aLong);
}
