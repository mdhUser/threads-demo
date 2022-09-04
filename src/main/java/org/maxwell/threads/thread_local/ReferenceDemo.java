package org.maxwell.threads.thread_local;

import lombok.extern.slf4j.Slf4j;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @description: java 四种引用 demo
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/9/4 10:10
 */
class MyObject {

    @Override
    protected void finalize() throws Throwable {
        System.out.println("-----invoke finalize method~ !!!");
    }
}

@Slf4j
public class ReferenceDemo {


    public static void main(String[] args) {

        MyObject myObject = new MyObject();
        ReferenceQueue<MyObject> referenceQueue = new ReferenceQueue<>();
        PhantomReference<MyObject> phantomReference = new PhantomReference<>(myObject, referenceQueue);

        List<byte[]> list = new ArrayList<>();

        new Thread(() -> {
            while (true) {
                list.add(new byte[1 * 1024 * 1024]);
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.info("{}\t list add ok", phantomReference.get());
            }
        }, "t1").start();

        new Thread(() -> {
            while (true) {
                Reference<? extends MyObject> reference = referenceQueue.poll();
                if (reference != null) {
                    log.info("---- 有虚对象回收加入队列");
                }
            }

        }, "t2").start();


    }

    private static void weakReference() {
        WeakReference<MyObject> weakReference = new WeakReference<>(new MyObject());
        log.info("---- gc after内存够用：{}", weakReference.get());

        System.gc();

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.info("---- gc after内存够用：{}", weakReference.get());
    }

    private static void softReference() {
        SoftReference<MyObject> softReference = new SoftReference<>(new MyObject());

        System.gc();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.info("---- gc after内存够用：{}", softReference.get());

        try {
            byte[] bytes = new byte[20 * 1024 * 1024];
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            log.info("---- gc after内存不够用：{}", softReference.get());
        }
    }

    private static void strongReference() {
        MyObject myObject = new MyObject();
        log.info("gc before:{}", myObject);

        myObject = null;
        //人工开启gc
        System.gc();
        log.info("gc after:{}", myObject);
    }

}
