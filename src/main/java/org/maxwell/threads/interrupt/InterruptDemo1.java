package org.maxwell.threads.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @description: 线程中断协商演示
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/13 22:10
 */
public class InterruptDemo1 {

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 300; i++) {
                System.out.println("----: " + i);
            }
            System.out.println("t1线程调用isInterrupt方法后的中断标识02:" + Thread.currentThread().isInterrupted());
        }, "t1");

        t1.start();
        System.out.println("t1线程默认的中断标识：" + t1.isInterrupted());

        TimeUnit.MILLISECONDS.sleep(2);
        t1.interrupt();
        System.out.println("t1线程调用interrupt方法后的中断标识01：" + t1.isInterrupted());

        TimeUnit.MILLISECONDS.sleep(2000);
        //jdk17不会自动恢复中断标识所以还是true
        System.out.println("t1线程调用interrupt方法后的中断标识03：" + t1.isInterrupted());

    }

}