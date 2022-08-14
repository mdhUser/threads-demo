package org.maxwell.threads.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @description: 线程中断协商演示
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/13 22:10
 */
public class InterruptDemo {

    static volatile boolean isStop = false;
    static AtomicBoolean bool = new AtomicBoolean(false);

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            for (; ; ) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "\t interrupted被标记为true，程序停止~~~");
                    break;
                }
            }
        }, "t1");
        t1.start();
        TimeUnit.MILLISECONDS.sleep(2000);
        new Thread(t1::interrupt, "t2").start();
    }

    //使用volatile修饰变量
    private static void m1() throws InterruptedException {
        new Thread(() -> {
            for (; ; ) {
                if (isStop) {
                    System.out.println(Thread.currentThread().getName() + "\t isStop被修改，程序停止~~~");
                    break;
                }
                //此处不能sout，sout源码中已经加了同步锁
//                System.out.println("执行线程== \t" + Thread.currentThread().getName());
            }
        }, "t1").start();

        TimeUnit.MILLISECONDS.sleep(2000);
        Runnable run = () -> {
            isStop = true;
        };
        new Thread(run, "t2").start();
    }

    //使用atomic变量
    private static void m2() throws InterruptedException {
        new Thread(() -> {
            for (; ; ) {
                if (bool.get()) {
                    System.out.println(Thread.currentThread().getName() + "\t isStop被修改，程序停止~~~");
                    break;
                }
                //此处不能sout，sout源码中已经加了同步锁
//                System.out.println("执行线程== \t" + Thread.currentThread().getName());
            }
        }, "t1").start();

        TimeUnit.MILLISECONDS.sleep(2000);
        Runnable run = () -> {
            bool.set(true);
        };
        new Thread(run, "t2").start();
    }

}