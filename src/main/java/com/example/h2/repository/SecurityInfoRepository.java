package com.example.h2.repository;

import com.example.h2.entity.SecurityInfo;
import com.example.h2.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Entity;
import java.util.List;

public interface SecurityInfoRepository  extends JpaRepository<SecurityInfo, Long> {

    @Modifying
    @Query("update SecurityInfo u set u.quantity = ?1 where u.securityCode = ?2")
    int update(Integer quantity , String securityCode);

    @Query(value = "select s.id, s.securityCode , s.quantity from SecurityInfo s where s.securityCode = ?1")
    List<SecurityInfo> findByCode(String securityCode);

    @Modifying
    @Query("update SecurityInfo u set u.quantity = ?1 where u.id = ?2")
    int updateSecurityInfo(Integer quantity, Long id);
}
