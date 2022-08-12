package org.maxwell.threads.locks;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 公平锁 非公平锁 演示
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/12 22:18
 */
public class Ticket {


    private int number = 50;
    //默认非公平锁
    ReentrantLock lock = new ReentrantLock(true);

    public void sale() {
        lock.lock();
        try {
            if (number > 0) {
                number--;
                System.out.println(Thread.currentThread().getName() + "卖出第：\t" + (50 - number) +
                        "\t 还剩下" + number);
            }
        } finally {
            lock.unlock();
        }
    }
}

class Conductor {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
//        CompletableFuture.runAsync(() -> {
//            for (int i = 0; i < 55; i++)
//                ticket.sale();
//        }).thenRunAsync(() -> {
//            for (int i = 0; i < 55; i++)
//                ticket.sale();
//        }).thenRunAsync(() -> {
//            for (int i = 0; i < 55; i++)
//                ticket.sale();
//        }).join();

        new Thread(() -> {
            for (int i = 0; i < 55; i++)
                ticket.sale();
        }, "a").start();
        new Thread(() -> {
            for (int i = 0; i < 55; i++)
                ticket.sale();
        }, "b").start();
        new Thread(() -> {
            for (int i = 0; i < 55; i++)
                ticket.sale();
        }, "c").start();


    }


}