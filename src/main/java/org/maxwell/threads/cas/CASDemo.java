package org.maxwell.threads.cas;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/27 19:37
 */
@Slf4j
public class CASDemo {


    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5, 2022)
                + "\t" + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 2022)
                + "\t" + atomicInteger.get());

    }

}
