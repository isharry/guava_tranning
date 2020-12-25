package com.piperplatform.guava.utilities;

import java.util.concurrent.TimeUnit;

/**
 * @Author shiyoufeng
 * @Date 2020/12/25 10:14 上午
 * @Version 1.0
 */
public class ElapsedExample {
    public static void main(String[] args) throws InterruptedException {
        process("123456");
    }

    private static void process(String orderNo) throws InterruptedException {
        System.out.printf("start process the order %s\n", orderNo);
        long startNano = System.nanoTime();
        TimeUnit.SECONDS.sleep(1);
        System.out.printf("The orderNo %s process successful and elapsed %d ns.", orderNo, System.nanoTime() - startNano);
    }
}
