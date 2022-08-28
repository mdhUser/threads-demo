package org.maxwell.threads.cas;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @description: CAS实现自旋锁
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/28 12:59
 */
@Slf4j
public class SpinLockDemo {


    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock() {
        Thread thread = Thread.currentThread();
        log.info("{} ---come in", thread.getName());
        while (!atomicReference.compareAndSet(null, thread)) {
        }
    }

    public void unLock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        log.info("{}\t -----task over,unLock...", thread.getName());
    }

    public static void main(String[] args) {

        SpinLockDemo lock = new SpinLockDemo();

        new Thread(() -> {
            lock.lock();
            //暂停几秒
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            lock.unLock();
        }, "t1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
            lock.lock();
            lock.unLock();
        }, "t2").start();

    }


}