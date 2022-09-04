package org.maxwell.threads.thread_local;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 阿里开发使用规范demo
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/9/3 13:02
 */

class MyData {

    ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

    public void add() {
        threadLocal.set(threadLocal.get() + 1);
    }

}

@Slf4j
public class ThreadLocalDemo1 {


    public static void main(String[] args) {

        MyData myData = new MyData();

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        try {
            for (int i = 0; i < 10; i++) {
                threadPool.submit(() -> {
                    Integer before;
                    Integer after;
                    try {
                        before = myData.threadLocal.get();
                        myData.add();
                        after = myData.threadLocal.get();
                    } finally {
                        //清空缓存值防止线程复用
                        myData.threadLocal.remove();
                    }
                    log.info("{} 线程之前：{},之后：{}", Thread.currentThread().getName(), before, after);
                });
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            threadPool.shutdown();
        }

    }

}
