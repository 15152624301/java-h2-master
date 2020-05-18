package com.example.h2.controller;

import com.example.h2.Data.TransactionsData;
import com.example.h2.entity.SecurityInfo;
import com.example.h2.entity.Transactions;
import com.example.h2.services.SecurityInfoService;
import com.example.h2.services.TradeInfoService;
import com.example.h2.services.TransactionsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/demo")
public class TransactionsController {

    @Autowired
    TransactionsService transactionsService;
    @Autowired
    TradeInfoService tradeInfoService;
    @Autowired
    SecurityInfoService securityInfoService;

    /**
     * 保存流水
     * */
    @PostMapping("/saveTransactions")
    public String saveTransactions(@RequestBody TransactionsData insertData) {
        Transactions model = new Transactions();
        BeanUtils.copyProperties(insertData,model);
        return transactionsService.saveTransactions(model);
    }

    /**
     * 查询流水
     * */
    @RequestMapping("/queryTransactionsDetail")
    public List<Transactions> queryTransactionsDetail() {
        return transactionsService.queryTransactionsDetail();
    }

    /**
     * 查询当前结果
     * */
    @RequestMapping("/findAllSecurityInfo")
    public List<SecurityInfo> findAllSecurityInfo() {
        return securityInfoService.findAllSecurityInfo();
    }


}
