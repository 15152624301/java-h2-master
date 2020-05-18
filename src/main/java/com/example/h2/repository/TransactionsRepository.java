package com.example.h2.repository;

import com.example.h2.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionsRepository  extends JpaRepository<Transactions, Long> {

    @Query("select s.securityCode , s.quantity , s.id from Transactions s where s.securityCode = ?1")
    List<Transactions> queryBySecurityCode(String securityCode);
}
