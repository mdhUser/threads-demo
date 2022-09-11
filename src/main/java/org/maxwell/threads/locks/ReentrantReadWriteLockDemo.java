package org.maxwell.threads.locks;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description:
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/9/11 13:29
 */
@Slf4j
class Recourse {

    Map<String, String> map = new HashMap<>();
    //重入锁等价于synchronized
    Lock lock = new ReentrantLock();
    //读读共享，读写互斥
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void write(String key, String value) {
        readWriteLock.writeLock().lock();
        try {
            log.info("{} 线程开始写入~~~", Thread.currentThread().getName());
            map.put(key, value);
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("{} 线程写入完成~~~", Thread.currentThread().getName());
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void read(String key) {
        readWriteLock.readLock().lock();
        try {
            log.info("{} 线程开始读取~~~~", Thread.currentThread().getName());
            String res = map.get(key);
//            try {
//                TimeUnit.MILLISECONDS.sleep(200);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            //演示读锁没有完成之前写锁无法获得
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("{} 线程读取完成 Result={}", Thread.currentThread().getName(), res);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }


}

public class ReentrantReadWriteLockDemo {


    public static void main(String[] args) {
        Recourse recourse = new Recourse();

        //write 线程
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            new Thread(() -> {
                recourse.write(finalI + "", finalI + "");
            }, String.valueOf(i)).start();
        }

        //read 线程
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            new Thread(() -> {
                recourse.read(finalI + "");
            }, String.valueOf(i)).start();
        }

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < 3; i++) {
            int finalI = i;
            new Thread(() -> {
                recourse.write(finalI + "", finalI + "");
            }, String.valueOf("新写锁~~  "+i)).start();
        }

    }


}
