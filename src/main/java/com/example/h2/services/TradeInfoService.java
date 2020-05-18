package com.example.h2.services;

import com.example.h2.entity.TradeInfo;
import com.example.h2.repository.TradeInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeInfoService {

    @Autowired
    private TradeInfoRepository tradeInfoRepository;

    public List<TradeInfo> findAll() {
       return tradeInfoRepository.findAll();
    }
}
