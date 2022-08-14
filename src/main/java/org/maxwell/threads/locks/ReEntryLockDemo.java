package org.maxwell.threads.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/12 23:31
 */
public class ReEntryLockDemo {

    Object o = new Object();

    public void m1() {
        synchronized (o) {
            System.out.println("this is a lock");
            m2();
        }
    }

    public synchronized void m2() {
        System.out.println("this is sync method");
        m3();
    }

    public static synchronized void m3() {
        System.out.println("this is a static sync method");
    }

    public static void main(String[] args) {
//        new ReEntryLockDemo().m1();
        Lock lock = new ReentrantLock();
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t -----come in 外层调用");
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "\t -----come in 内层调用");
                } finally {
//                    lock.unlock();
                }
            } finally {
                lock.unlock();
            }

        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t ----获取锁执行");
            } finally {
                lock.unlock();
            }
        }, "t2").start();


    }

}
