package org.maxwell.threads.sync;

import lombok.extern.slf4j.Slf4j;

/**
 * @description: 锁消除demo
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/9/4 20:01
 */
@Slf4j
public class LockClearUPDemo {

    static Object lock = new Object();

    public void m1() {
//        synchronized (lock) {
//            log.info("----- fuck U");
//        }
        //每次都创建一个新的锁对象没有意义，JIT编译器会无视它
        Object o = new Object();
        synchronized (o) {
            log.info("");
        }
    }

    public static void main(String[] args) {

        LockClearUPDemo lockClearUPDemo = new LockClearUPDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                lockClearUPDemo.m1();
            }, String.valueOf(i)).start();
        }

    }


}
