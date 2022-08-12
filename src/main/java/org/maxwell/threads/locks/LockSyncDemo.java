package org.maxwell.threads.locks;

/**
 * @description:
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/12 21:28
 */
public class LockSyncDemo {

    Object o = new Object();

    public void m1() {
        synchronized (o) {
            System.out.println("this is a lock");
        }
    }

    public synchronized void m2() {
        System.out.println("this is sync method");
    }

    public static synchronized void m3() {
        System.out.println("this is sync method");
    }

    public static void main(String[] args) {

    }

}
