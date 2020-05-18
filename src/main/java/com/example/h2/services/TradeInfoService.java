package com.example.h2.services;

import com.example.h2.entity.TradeInfo;
import com.example.h2.repository.TradeInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradeInfoService {

    @Autowired
    private TradeInfoRepository tradeInfoRepository;

    public Optional<TradeInfo> findTradeInfo(Long id) {
        return tradeInfoRepository.findById(id);
    }

    public TradeInfo save(TradeInfo tradeInfo){
        TradeInfo save = tradeInfoRepository.save(tradeInfo);
        return save;
    }

    public List<TradeInfo> findAll() {
       return tradeInfoRepository.findAll();
    }
}
