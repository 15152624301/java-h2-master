package com.example.h2.controller;

import com.example.h2.JavaH2ApplicationTests;
import com.example.h2.entity.SecurityInfo;
import com.example.h2.entity.Transactions;
import com.example.h2.services.SecurityInfoService;
import com.example.h2.services.TradeInfoService;
import com.example.h2.services.TransactionsService;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class TransactionsControllerTest extends JavaH2ApplicationTests {

    @Autowired
    TransactionsService transactionsService;
    @Autowired
    SecurityInfoService securityInfoService;
    @Autowired
    TradeInfoService tradeInfoService;

    @Test
    public void initData() {
        final List<Transactions> transactions = transactionsService.queryTransactionsDetail();
        assertEquals("插入了0条记录",0,transactions.size());
        final Transactions transactions1 = new Transactions(1,1,"REL",50,"INSERT","Buy");
        transactionsService.saveTransactions(transactions1);
        final Transactions transactions2 = new Transactions(2,2,"REL",60,"UPDATE","Buy");
        transactionsService.saveTransactions(transactions2);
        final Transactions transactions3 = new Transactions(1,1,"ITC",40,"INSERT","Sell");
        transactionsService.saveTransactions(transactions3);
        final Transactions transactions4 = new Transactions(2,2,"ITC",30,"CANCEL","Buy");
        transactionsService.saveTransactions(transactions4);
        final Transactions transactions5 = new Transactions(1,1,"INF",70,"INSERT","Buy");
        transactionsService.saveTransactions(transactions5);
        final Transactions transactions6 = new Transactions(2,1,"INF",20,"INSERT","Sell");
        transactionsService.saveTransactions(transactions6);
        final List<Transactions> transactions7 = transactionsService.queryTransactionsDetail();
        assertEquals("插入了6条记录",6,transactions7.size());

        final List<SecurityInfo> allSecurityInfo = securityInfoService.findAllSecurityInfo();
        final List<SecurityInfo> rel1 = allSecurityInfo.stream().filter(a -> a.getSecurityCode().equals("REL")).collect(Collectors.toList());
        final List<SecurityInfo> rel2 = allSecurityInfo.stream().filter(a -> a.getSecurityCode().equals("ITC")).collect(Collectors.toList());
        final List<SecurityInfo> rel3 = allSecurityInfo.stream().filter(a -> a.getSecurityCode().equals("INF")).collect(Collectors.toList());
        assertEquals(allSecurityInfo.get(0).getQuantity(),securityInfoService.findAllSecurityInfo().get(0).getQuantity());
        assertEquals("最终结果集3条",3,allSecurityInfo.size());

        assertEquals("编号是REL的结果",60,rel1.get(0).getQuantity().intValue());
        assertEquals("编号是ITC的结果",0 ,rel2.get(0).getQuantity().intValue());
        assertEquals("编号是INF的结果",rel3.get(0).getQuantity().intValue(),50);

        assertEquals("3组数据的数量和",110, rel1.get(0).getQuantity()+rel2.get(0).getQuantity()+rel3.get(0).getQuantity());
    }


}