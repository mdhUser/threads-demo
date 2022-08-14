package org.maxwell.threads.lock_support;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/14 15:56
 */
public class LockSupportDemo {

    public static void main(String[] args) {



    }

    private static void awaitSignal() {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "\t ---come in");
                condition.await();
                System.out.println(Thread.currentThread().getName() + "\t ----被唤醒");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + "\t ---发出通知");
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }

    private static void waitNotify() {
        new Thread(() -> {
            synchronized ("lock") {
                System.out.println(Thread.currentThread().getName() + "\t ---come in");
                try {
                    "lock".wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "\t ----被唤醒");
            }
        }, "t1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
            synchronized ("lock") {
                "lock".notify();
            }
            System.out.println(Thread.currentThread().getName() + "\t ---发出通知");
        }, "t2").start();
    }

}
