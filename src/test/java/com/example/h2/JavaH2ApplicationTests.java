package com.example.h2;

import com.example.h2.entity.SecurityInfo;
import com.example.h2.entity.Transactions;
import com.example.h2.services.SecurityInfoService;
import com.example.h2.services.TradeInfoService;
import com.example.h2.services.TransactionsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaH2ApplicationTests {

    @Autowired
    SecurityInfoService securityInfoService;

    @Autowired
    TradeInfoService tradeInfoService;

    @Autowired
    TransactionsService transactionsService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void saveTransactions() {
        final Transactions transactions = new Transactions(1,1,"REL",50,"INSERT","Buy");
        transactionsService.saveTransactions(transactions);
    }

    @Test
    public void queryTransactionsDetail() {
        transactionsService.queryTransactionsDetail();
    }

    @Test
    public void queryTradeInfoDetail() {
        tradeInfoService.findAll();
    }

    @Test
    public void querySecurityInfoDetail() {
        securityInfoService.save( new SecurityInfo("REL",50));
    }

    @Test
    public void updateSecurityInfo() {
        securityInfoService.updateSecurityInfo( 30,3L);
    }

    @Test
    public void findAllSecurityInfo() {
        securityInfoService.findAllSecurityInfo();
    }
}

