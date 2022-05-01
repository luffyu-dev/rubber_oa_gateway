package com.rubber.oa.gateway.dao;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * @author luffyu
 * Created on 2022/4/23
 */
@Service
public class RubberDaoTest implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(">>>>>>");
    }
}
