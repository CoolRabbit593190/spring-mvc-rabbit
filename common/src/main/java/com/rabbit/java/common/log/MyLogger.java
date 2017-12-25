package com.rabbit.java.common.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyLogger {

    public static void inf(Class clz, String arg0, Object... args) {
        Logger logger = LoggerFactory.getLogger(clz);
        logger.info(arg0, args);
    }

    public static void err(String arg0, Object... args) {
        Logger errlogger = LoggerFactory.getLogger("dailyErrLog");
        errlogger.error(arg0, args);

    }
}
