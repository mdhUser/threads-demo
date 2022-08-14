package org.maxwell.threads.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @description: 线程中断协商演示
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/13 22:10
 */
public class InterruptDemo2 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (; ; ) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "\t"
                            + "线程停止");
                    break;
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("this is " + Thread.currentThread().getName());
            }
        }, "t1");
        t1.start();
        TimeUnit.MILLISECONDS.sleep(1000);
        new Thread(t1::interrupt, "t2").start();
    }

}