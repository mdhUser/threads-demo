package org.maxwell.threads.cas.atomic;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLongArray;

/**
 * @description: 原子类之数组类型原子类
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/28 16:03
 */
public class AtomicIntegerArrayDemo {


    public static void main(String[] args) {

        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(new int[5]);

        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            System.out.println(atomicIntegerArray.get(i));
        }

        int andSet = atomicIntegerArray.getAndSet(0, 12);
        System.out.println(andSet + "\t" + atomicIntegerArray.get(0));

        int andIncrement = atomicIntegerArray.getAndIncrement(0);
        System.out.println(andIncrement + "\t" + atomicIntegerArray.get(0));

    }


}
