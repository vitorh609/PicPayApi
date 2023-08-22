package com.servicepay.servicePay.domain.transacition.repository;

import com.servicepay.servicePay.domain.transacition.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
