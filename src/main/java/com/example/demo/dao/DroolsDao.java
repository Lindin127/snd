package com.example.demo.dao;

import com.example.demo.bean.Drools;
import org.apache.ibatis.annotations.Select;

/**
 * Created by lind on 2020/8/17.
 */
public interface DroolsDao {



        @Select("select * from drools where code =#{code} ")
        Drools selectByYhid(String code);

}
