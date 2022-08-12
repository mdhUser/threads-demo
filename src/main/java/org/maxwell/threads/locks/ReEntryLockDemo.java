package org.maxwell.threads.locks;

/**
 * @description:
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/12 23:31
 */
public class ReEntryLockDemo {

    Object o = new Object();

    public void m1() {
        synchronized (o) {
            System.out.println("this is a lock");
            m2();
        }
    }

    public synchronized void m2() {
        System.out.println("this is sync method");
        m3();
    }

    public static synchronized void m3() {
        System.out.println("this is a static sync method");
    }

    public static void main(String[] args) {
        new ReEntryLockDemo().m1();
    }

}
