package org.maxwell.threads.vol;

import java.util.concurrent.TimeUnit;

/**
 * @description: volatile 非原子性案例
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/27 16:47
 */
class MyNumber {

    volatile int number;

    public void addPlus() {
        number++;
    }

}

public class VolatileNoAtomicDemo {

    public static void main(String[] args) {

        MyNumber myNumber = new MyNumber();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myNumber.addPlus();
                }
            }, String.valueOf(i)).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 使用volatile会重复写入相同值导致结果不为10000
        System.out.println(myNumber.number);

    }


}
