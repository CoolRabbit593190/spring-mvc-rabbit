package com.rabbit.java.controller.aspect;

import com.rabbit.java.common.annotation.EXHandle;
import com.rabbit.java.common.log.MyLogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionHandleAspect {

    @Pointcut("within(com.rabbit.java.controller..*)&&@annotation(xh)")
    public void performance(EXHandle xh) {
    }

    @AfterThrowing(throwing = "ex", pointcut = "performance(xh)")
    public void AfterThrowing(JoinPoint jp, Throwable ex, EXHandle xh) {
        Class declaringType = jp.getSignature().getDeclaringType();
        String methodName = jp.getSignature().getName();
        MyLogger.inf(declaringType, xh.desc());
        MyLogger.err("Method : {}, Err : {}", methodName, ex.getMessage(), ex);
    }


}
