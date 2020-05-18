package com.example.h2.services;

import com.example.h2.JavaH2ApplicationTests;
import com.example.h2.entity.Transactions;
import com.example.h2.repository.TransactionsRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class TransactionsServiceTest extends JavaH2ApplicationTests {

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Test
    public void saveTransactions() {
        final Transactions transactions1 = new Transactions(1,1,"REL",50,"INSERT","Buy");
        final Transactions save = transactionsRepository.save(transactions1);
        assertEquals("插入返回50条",50,save.getQuantity().intValue());

    }

    @Test
    public void queryTransactionsDetail() {
        final Transactions transactions1 = new Transactions(1,1,"REL",50,"INSERT","Buy");
        final Transactions save = transactionsRepository.save(transactions1);
        assertEquals("最终结果集1条",1,transactionsRepository.findAll().size());

    }
}