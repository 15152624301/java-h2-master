package com.example.h2.services;

import com.example.h2.JavaH2ApplicationTests;
import com.example.h2.repository.TradeInfoRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class TradeInfoServiceTest extends JavaH2ApplicationTests {

    @Autowired
    private TradeInfoRepository tradeInfoRepository;

    @Test
    public void findAll() {
        assertEquals("最终结果集0条",0,tradeInfoRepository.findAll().size());
    }
}