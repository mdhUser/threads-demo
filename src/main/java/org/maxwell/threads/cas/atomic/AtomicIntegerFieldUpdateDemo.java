package org.maxwell.threads.cas.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @description:
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/28 15:34
 */
class BankAccount {

    String name = "NB";
    volatile int money = 0;

    public void add() {
        money += 100;
    }

    AtomicIntegerFieldUpdater<BankAccount> fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(BankAccount.class, "money");
    //不加synchronized , 保证高性能原子性，局部使用原子类属性修改器
    public void transMoney() {
        fieldUpdater.addAndGet(this,100);
    }

}

public class AtomicIntegerFieldUpdateDemo {

    public static void main(String[] args) {

        BankAccount bankAccount = new BankAccount();
        CountDownLatch count = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 1000; j++) {
                        bankAccount.transMoney();
                    }
                } finally {
                    count.countDown();
                }
            }, String.valueOf(i)).start();
        }

        try {
            count.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + "\t result==" + bankAccount.money);

    }

}
