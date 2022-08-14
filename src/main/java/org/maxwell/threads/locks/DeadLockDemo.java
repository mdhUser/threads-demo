package org.maxwell.threads.locks;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/13 20:11
 */
public class DeadLockDemo {


    public static void main(String[] args) {

        final Object objA = new Object();
        final Object objB = new Object();

        new Thread(() -> {
            synchronized (objA) {
                System.out.println("this is  lock A");
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (objB) {
                    System.out.println("this is lock B");
                }
            }
        },"t1").start();


        new Thread(() -> {
            synchronized (objB) {
                System.out.println("this is  lock B");
                synchronized (objA) {
                    System.out.println("this is lock A");
                }
            }
        },"t2").start();



    }


}
