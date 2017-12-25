package com.rabbit.java.controller.aspect;

import com.rabbit.java.common.annotation.DataSource;
import com.rabbit.java.common.datasource.readwrite.split.DynamicDataSourceHolder;
import com.rabbit.java.common.enums.MasterSlave;
import com.rabbit.java.common.log.MyLogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Mr.Rabbit
 */
@Aspect
@Component
@Order(-1)
public class DataSourceAspect {

    @Pointcut("within(com.rabbit.java.service.impl..*)&&@annotation(ds)")
    public void performance(DataSource ds) {
    }

    @Before("performance(ds)")
    public void before(JoinPoint point, DataSource ds) {
        MyLogger.inf(DataSourceAspect.class,"before advise...");
        if (MasterSlave.MASTER.equals(ds.value())) {
            DynamicDataSourceHolder.markMaster();
            MyLogger.inf(DataSourceAspect.class, "DataSouce : {}", ds.value().getType());
        } else {
            DynamicDataSourceHolder.markSlave();
            MyLogger.inf(DataSourceAspect.class, "DataSouce : {}", ds.value().getType());
        }

    }
}
