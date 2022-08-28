package org.maxwell.threads.cas.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @description: 可标记是否被修改
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/28 15:11
 */
public class AtomicMarkableReferenceDemo {

    static AtomicMarkableReference<Integer> markableReference = new AtomicMarkableReference<>(100, false);

    public static void main(String[] args) {

        new Thread(() -> {
            boolean marked = markableReference.isMarked();
            System.out.println(Thread.currentThread().getName() + "\t 默认标识：" + marked);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            markableReference.compareAndSet(100, 1000, marked, !marked);
            System.out.println(markableReference.getReference() + "\t :: result=" + markableReference.isMarked());
        }, "t1").start();

        new Thread(() -> {
            boolean marked = markableReference.isMarked();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            boolean b = markableReference.compareAndSet(1000, 2000, marked, !marked);
            System.out.println(markableReference.getReference() + "\t :: result=" + b);
        }, "t2").start();


    }


}
