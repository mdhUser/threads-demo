package org.maxwell.threads.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/9/4 16:09
 */

@Slf4j
class Ticket {
    private int number = 50;
    Object lock = new Object();

    public void sale() {
        synchronized (lock) {
            if (number > 0) {
                log.info("{} 卖出第：\t {} 张,还剩：{}", Thread.currentThread().getName(), number--, number);
            }
        }
    }

}

public class TicketDemo {

    public static void main(String[] args) {

        Ticket ticket = new Ticket();

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
        new Thread(() -> {
            for (int i = 0; i < 55; i++)
                ticket.sale();
        }, "d").start();
        new Thread(() -> {
            for (int i = 0; i < 55; i++)
                ticket.sale();
        }, "e").start();

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
