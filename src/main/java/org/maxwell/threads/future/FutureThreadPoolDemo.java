package org.maxwell.threads.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/8 23:58
 */
public class FutureThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        FutureTask<String> futureTask1= new FutureTask<>(()->{
            TimeUnit.MILLISECONDS.sleep(500);
            return "tas1 over";
        });
        threadPool.submit(futureTask1);

        FutureTask<String> futureTask2= new FutureTask<>(()->{
            TimeUnit.MILLISECONDS.sleep(300);
            return "tas2 over";
        });
        threadPool.submit(futureTask2);


        TimeUnit.MILLISECONDS.sleep(300);
        long end = System.currentTimeMillis();
        System.out.println("=====costTime: "+(end-start)+"毫秒");
        threadPool.shutdown();
        System.out.println(Thread.currentThread().getName()+"\t ---end");
        m1();

    }

    private static void m1() throws InterruptedException {
        long start = System.currentTimeMillis();
        TimeUnit.MILLISECONDS.sleep(500);
        TimeUnit.MILLISECONDS.sleep(300);
        TimeUnit.MILLISECONDS.sleep(300);
        long end = System.currentTimeMillis();
        System.out.println((end-start)+"ms");
        System.out.println(Thread.currentThread().getName()+"\t ---end");
    }

}
