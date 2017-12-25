package com.rabbit.java.common.exception.lifepay;


import com.rabbit.java.common.exception.CommonException;
import com.rabbit.java.common.result.CommonErrorMessage;

public class LifePayException extends CommonException {
    public LifePayException(CommonErrorMessage message) {
        super(message);
    }
}
