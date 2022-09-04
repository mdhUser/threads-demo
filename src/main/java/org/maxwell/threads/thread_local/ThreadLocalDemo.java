package org.maxwell.threads.thread_local;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * @description:
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/9/3 12:31
 */

class House {

    LongAdder saleCount = new LongAdder();

    public void saleHouse() {
        saleCount.increment();
    }

    //每个线程私有的变量副本
    ThreadLocal<Integer> saleVolume = ThreadLocal.withInitial(() -> 0);

    public void setSaleVolumeByThreadLocal() {
        saleVolume.set(1 + saleVolume.get());
    }

}

@Slf4j
public class ThreadLocalDemo {

    static House house = new House();

    public static void main(String[] args) {

        for (int j = 0; j < 5; j++) {
            new Thread(() -> {
                try {
                    int size = ThreadLocalRandom.current().nextInt(5) + 1;
                    for (int i = 0; i < size; i++) {
                        house.saleHouse();
                        house.setSaleVolumeByThreadLocal();
                    }
                } finally {

                }
                log.info("{}\t号销售卖出：{}", Thread.currentThread().getName(), house.saleVolume.get());
            }, String.valueOf(j)).start();
        }

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("销售总量：" + house.saleCount.sum());

    }


}
