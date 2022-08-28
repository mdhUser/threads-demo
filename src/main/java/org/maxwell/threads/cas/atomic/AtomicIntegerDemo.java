package org.maxwell.threads.cas.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 原子类计数demo
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/28 13:50
 */
@Slf4j
class Adder {
    AtomicInteger atomicInteger = new AtomicInteger();
    public void add() {
        atomicInteger.getAndIncrement();
    }
}

@Slf4j
public class AtomicIntegerDemo {


    public static void main(String[] args) {
        Adder adder = new Adder();
        //使用CDL让线程执行完
        CountDownLatch count = new CountDownLatch(50);
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 1000; j++) {
                        adder.add();
                    }
                } finally {
                    count.countDown();
                }
            }, String.valueOf(i)).start();
        }
//   实际开发中不能直接让主线程阻塞
//        try {
//            TimeUnit.MILLISECONDS.sleep(2000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        try {
            count.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("{}\t result:{}", Thread.currentThread().getName(), adder.atomicInteger.get());
    }
}