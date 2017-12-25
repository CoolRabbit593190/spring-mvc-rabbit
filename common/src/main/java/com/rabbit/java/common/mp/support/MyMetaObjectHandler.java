package com.rabbit.java.common.mp.support;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 自定义填充处理器
 */
public class MyMetaObjectHandler extends MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("lastModifyDate", new Timestamp(System.currentTimeMillis()), metaObject);
    }


    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("lastModifyDate", new Timestamp(System.currentTimeMillis()), metaObject);
    }

}
