package com.rabbit.java.common.annotation;

import com.rabbit.java.common.enums.MasterSlave;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource {
    MasterSlave value() default MasterSlave.MASTER;
}
