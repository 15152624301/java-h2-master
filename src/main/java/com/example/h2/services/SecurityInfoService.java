package com.example.h2.services;

import com.example.h2.entity.SecurityInfo;
import com.example.h2.repository.SecurityInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityInfoService {

    @Autowired
    private SecurityInfoRepository securityInfoRepository;

    public List<SecurityInfo> findAllSecurityInfo() {
        return securityInfoRepository.findAll();
    }

    public SecurityInfo save(SecurityInfo securityInfo){
       return securityInfoRepository.save(securityInfo);
    }

    public int updateSecurityInfo(Integer quantity, Long id) {
      return   securityInfoRepository.updateSecurityInfo(quantity,id);
    }
}
