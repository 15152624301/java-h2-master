package com.example.h2.repository;

import com.example.h2.entity.TradeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TradeInfoRepository extends JpaRepository<TradeInfo, Long> {

    @Modifying
    @Query(value = "update TradeInfo u set u.number = ?1 where u.id = ?2")
    int update( Integer number , Long id );
}
