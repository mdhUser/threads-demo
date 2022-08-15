package org.maxwell.threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/15 13:59
 */
public class CdTest {

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(10);

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        threadPool.submit(() -> {
            System.out.println("this is 1 over~~~\t");
            countDownLatch.countDown();
        });
        threadPool.submit(() -> {
            System.out.println("this is 2 over~~~\t");
            countDownLatch.countDown();
        });
        threadPool.submit(() -> {
            System.out.println("this is 3 over~~~\t");
            countDownLatch.countDown();
        });
        threadPool.submit(() -> {
            System.out.println("this is 4 over~~~\t");
            countDownLatch.countDown();
        });
        threadPool.submit(() -> {
            System.out.println("this is 5 over~~~\t");
            countDownLatch.countDown();
        });
        threadPool.submit(() -> {
            System.out.println("this is 6 over~~~\t");
            countDownLatch.countDown();
        });
        threadPool.submit(() -> {
            System.out.println("this is 7 over~~~\t");
            countDownLatch.countDown();
        });
        threadPool.submit(() -> {
            System.out.println("this is 8 over~~~\t");
            countDownLatch.countDown();
        });
        threadPool.submit(() -> {
            System.out.println("this is 9 over~~~\t");
            countDownLatch.countDown();
        });
        threadPool.submit(() -> {
            System.out.println("this is 10 over~~~\t");
            countDownLatch.countDown();
        });

        try {
            System.out.println("等待十个任务执行完毕~~~");
            countDownLatch.await();
            System.out.println("十个线程执行完毕~~~ \t 继续执行主线程");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        threadPool.shutdown();

    }


}
