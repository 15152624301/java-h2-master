package com.example.h2.services;

import com.example.h2.JavaH2ApplicationTests;
import com.example.h2.entity.SecurityInfo;
import com.example.h2.repository.SecurityInfoRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class SecurityInfoServiceTest extends JavaH2ApplicationTests {

    @Autowired
    private SecurityInfoRepository securityInfoRepository;

    @Test
    @Transactional
    public void findAllSecurityInfo() {
        securityInfoRepository.save(new SecurityInfo("REL",60));
        final List<SecurityInfo> all1 = securityInfoRepository.findAll();
        assertEquals("最终结果集1条",1,securityInfoRepository.findAll().size());
        assertEquals("这条数据的数量是60",60,securityInfoRepository.findAll().get(0).getQuantity().intValue());
        final List<SecurityInfo> all = securityInfoRepository.findAll();
        int i = securityInfoRepository.updateSecurityInfo(80,all.get(0).getId());
        assertEquals("更新成功",1,i);
        assertEquals("最终结果集1条",1,securityInfoRepository.findAll().size());
        securityInfoRepository.findAll();
    }

    @Test
    public void save() {
        final SecurityInfo save = securityInfoRepository.save(new SecurityInfo("REL", 60));
        assertEquals("这条数据的数量是60",60,save.getQuantity().intValue());

    }

    @Test
    @Transactional
    public void updateSecurityInfo() {
        securityInfoRepository.save(new SecurityInfo("REL",60));
        final int info = securityInfoRepository.updateSecurityInfo(80, 1L);
        assertEquals("更新成功",1,info);
    }
}