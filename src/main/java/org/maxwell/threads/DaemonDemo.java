package org.maxwell.threads;

import java.util.concurrent.TimeUnit;

/**
 * @description: 守护线程演示
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/8 23:17
 */
public class DaemonDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t"
                    + (Thread.currentThread().isDaemon() ? "守护线程" : "用户线程"));
            for (;;){
            }
        },"t1");
        t1.setDaemon(true);
        t1.start();

        TimeUnit.SECONDS.sleep(2);

        System.out.println(Thread.currentThread().getName()+"\t ---end 主线程");
    }

}
