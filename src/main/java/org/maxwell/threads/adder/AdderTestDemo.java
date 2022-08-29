package org.maxwell.threads.adder;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * @description:
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/28 16:06
 */
class ClickNumber {

    int i;
    AtomicInteger atomicInteger = new AtomicInteger();
    LongAdder adder = new LongAdder();
    LongAccumulator accumulator = new LongAccumulator((x, y) -> x + y, 0);

    public synchronized void addBySynchronized() {
        i++;
    }

    public void addByAtomicInteger() {
        atomicInteger.getAndIncrement();
    }

    public void addByLongAdder() {
        adder.increment();
    }

    public void addByLongAcc() {
        accumulator.accumulate(1);
    }

}

public class AdderTestDemo {

    private static final int COUNT = 100000;
    private static final int THREAD_NUM = 50;

    public static void main(String[] args) {

        ClickNumber clickNumber = new ClickNumber();
        long end;
        long start;

        CountDownLatch count0 = new CountDownLatch(THREAD_NUM);
        CountDownLatch count1 = new CountDownLatch(THREAD_NUM);
        CountDownLatch count2 = new CountDownLatch(THREAD_NUM);
        CountDownLatch count3 = new CountDownLatch(THREAD_NUM);

        //synchronized
        start = System.currentTimeMillis();
        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < COUNT; j++) {
                        clickNumber.addBySynchronized();
                    }
                } finally {
                    count0.countDown();
                }
            }).start();
        }
        try {
            count0.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        end = System.currentTimeMillis();
        System.out.println("---costTime: " + (end - start) + "ms  --- Synchronized result:" + clickNumber.i);

        //atomicInteger
        start = System.currentTimeMillis();
        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < COUNT; j++) {
                        clickNumber.addByAtomicInteger();
                    }
                } finally {
                    count1.countDown();
                }
            }).start();
        }
        try {
            count1.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        end = System.currentTimeMillis();
        System.out.println("---costTime: " + (end - start) + "ms  --- AtomicInteger result:" + clickNumber.atomicInteger.get());

        //longAdder
        start = System.currentTimeMillis();
        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < COUNT; j++) {
                        clickNumber.addByLongAdder();
                    }
                } finally {
                    count2.countDown();
                }
            }).start();
        }
        try {
            count2.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        end = System.currentTimeMillis();
        System.out.println("---costTime: " + (end - start) + "ms  --- LongAdder result:" + clickNumber.adder.sum());

        //atomicAccumulator
        start = System.currentTimeMillis();
        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < COUNT; j++) {
                        clickNumber.addByLongAcc();
                    }
                } finally {
                    count3.countDown();
                }
            }).start();
        }
        try {
            count3.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        end = System.currentTimeMillis();
        System.out.println("---costTime: " + (end - start) + "ms  --- AtomicAccumulator result:" + clickNumber.accumulator.get());

    }

}