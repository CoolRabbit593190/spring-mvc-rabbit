package com.rabbit.java.controller;

import org.apache.log4j.Logger;

import java.text.MessageFormat;

/**
 * @author mr.rabbit
 */
public abstract class Base4ClientController {

    protected Logger logger = null;

    public Base4ClientController() {
        this.logger = Logger.getLogger(this.getClass());
    }

    protected void printInfo(String method, String logInfo) {
        try {
            logger.info(MessageFormat.format("Method : {0}, Info : {2}", method, logInfo));
        } catch (Exception e) {

        }
    }

    protected void printErr(String method, Throwable t) {
        try {
            logger.error(MessageFormat.format("Method : {0}, Err : ", method), t);
        } catch (Exception e) {

        }
    }

    protected String getCurrentMethodName() {
        String method = Thread.currentThread().getStackTrace()[2].getMethodName();
        return method;
    }
}
