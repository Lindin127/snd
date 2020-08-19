package com.example.demo.service.impl;

import com.example.demo.bean.Drools;
import com.example.demo.dao.DroolsDao;
import com.example.demo.service.DroolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lind on 2020/8/17.
 */
@Service
public class DroolsServiceImpl implements DroolsService {
    @Autowired
    private DroolsDao DroolsDao;

    @Override
    public String getValueByCode(String r_age) {
        Drools drool = DroolsDao.selectByYhid(r_age);
        return drool.getValue();
    }
}
