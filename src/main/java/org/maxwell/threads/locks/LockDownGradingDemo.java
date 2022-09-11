package org.maxwell.threads.locks;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description:
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/9/11 14:29
 */
public class LockDownGradingDemo {

    public static void main(String[] args) {

        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();


//        readLock.lock();
//        System.out.println("read ~~~");
//        readLock.unlock();
//
//        writeLock.lock();
//        System.out.println("write ~~~");
//        writeLock.unlock();

        //写锁可以降级读锁 ，读锁不能直接获取写锁
        writeLock.lock();
        System.out.println("获取写锁，开始写入 ~~~");
        readLock.lock();
        System.out.println("获取读锁, 写锁降级 ~~~");

        writeLock.unlock();
        readLock.unlock();


    }

}
