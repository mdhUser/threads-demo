package org.maxwell.threads.cas.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @description:
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/28 16:34
 */
@Slf4j
class Var {

    volatile Boolean isInit = Boolean.FALSE;

    static final AtomicReferenceFieldUpdater<Var, Boolean> fieldUpdater =
            AtomicReferenceFieldUpdater.newUpdater(Var.class, Boolean.class, "isInit");

    public void init() {
        if (fieldUpdater.compareAndSet(this, Boolean.FALSE, Boolean.TRUE)) {
            log.info("{}\t -----start init, need 2 seconds", Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("{}\t ----over init", Thread.currentThread().getName());
        } else {
            log.info("已有线程正在初始化~~~~");
        }
    }


}

public class AtomicReferenceFieldUpdaterDemo {

    public static void main(String[] args) {
        Var var = new Var();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                var.init();
            }, String.valueOf(i)).start();
        }
    }

}
