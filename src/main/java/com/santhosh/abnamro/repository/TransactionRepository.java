package com.santhosh.abnamro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santhosh.abnamro.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
