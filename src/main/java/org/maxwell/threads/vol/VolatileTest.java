package org.maxwell.threads.vol;

import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/8 22:14
 */
@Slf4j
public class VolatileTest {

    int i;
    volatile boolean flag;

    public void write() {
        i = 1;
        flag = true;
    }

    public void read() {
        if (flag) {
            log.info("----i = " + i);
        }
    }


    /**
     * volatile 适用情况
     */
    class Counter {

        private volatile int value;

        public int getValue() {
            return value; //利用volatile保证读取操作的可见性
        }

        public synchronized int increment() {
            return value++; //利用synchronized保证复合操作的原子性
        }
    }

}
