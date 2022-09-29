package com.fastcampus.ch4;

import com.fastcampus.ch4.controller.BoardController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class LogTest {
    private Logger logger = LogManager.getLogger(BoardController.class);

    @Test
    public void logTest(){
        logger.debug("log debug");
        logger.trace("log trace");
        logger.info("log info");
        logger.warn("log warn");
        logger.error("log error");
    }
}
