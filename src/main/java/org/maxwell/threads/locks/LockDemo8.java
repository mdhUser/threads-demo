package org.maxwell.threads.locks;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/12 19:03
 */
class Phone {

    public  synchronized void sendSMS() {
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("--------sendSMS");
    }

    public  synchronized void sendEmail() {
        System.out.println("--------sendEmail");
    }

    public void hello() {
        System.out.println("--------hello");
    }

}

public class LockDemo8 {

    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        new Thread(phone::sendSMS, "a").start();
        TimeUnit.MILLISECONDS.sleep(200);
        new Thread(phone::sendEmail, "b").start();
    }

}