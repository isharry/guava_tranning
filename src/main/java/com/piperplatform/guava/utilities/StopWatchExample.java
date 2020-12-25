package com.piperplatform.guava.utilities;

import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Author shiyoufeng
 * @Date 2020/12/25 10:20 上午
 * @Version 1.0
 */
public class StopWatchExample {
    private final static Logger logger = LoggerFactory.getLogger(StopWatchExample.class);
    public static void main(String[] args) throws InterruptedException {
        process("123456");
    }

    private static void process(String orderNo) throws InterruptedException {
        logger.info("start process the order [{}]", orderNo);
        Stopwatch stopwatch = Stopwatch.createStarted();
        TimeUnit.MILLISECONDS.sleep(100);
        logger.info("The orderNo [{}] process successful and elapsed [{}]", orderNo, stopwatch.stop());
    }
}
