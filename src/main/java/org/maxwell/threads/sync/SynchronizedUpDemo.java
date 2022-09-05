package org.maxwell.threads.sync;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/9/4 15:50
 */
@Slf4j
public class SynchronizedUpDemo {

    public static void main(String[] args) {

        Object o = new Object();
        log.info(ClassLayout.parseInstance(o).toPrintable());
        new Thread(() -> {
            synchronized (o) {
                log.info(ClassLayout.parseInstance(o).toPrintable());
            }
        }, "t1").start();


    }

    private static void hashWithBiased() {
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        Object o = new Object();
//        o.hashCode();
//        synchronized (o) {
//            log.info("本应是偏向锁，但由于计算过一致性hash，会直接升级为轻量级锁");
//            log.info(ClassLayout.parseInstance(o).toPrintable());
//        }

        Object o = new Object();
        synchronized (o) {
            o.hashCode();
            log.info("偏向锁过程中遇到一致性hash计算请求，立马撤销偏向锁模式，膨胀为重量级锁");
            log.info(ClassLayout.parseInstance(o).toPrintable());
        }
    }

    /**
     * 偏向锁
     */
    private static void biasedLock() {
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Object o = new Object();
        log.info(ClassLayout.parseInstance(o).toPrintable());
        new Thread(() -> {
            synchronized (o) {
                log.info(ClassLayout.parseInstance(o).toPrintable());
            }
        }, "t1").start();
    }

}
