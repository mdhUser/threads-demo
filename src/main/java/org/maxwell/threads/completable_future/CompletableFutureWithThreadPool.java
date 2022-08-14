package org.maxwell.threads.completable_future;

import java.util.concurrent.*;

/**
 * @description:
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/12 17:22
 */
public class CompletableFutureWithThreadPool {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                3,
                5,
                3L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        try {
            Void unused = CompletableFuture.supplyAsync(() -> {
                System.out.println("1号任务" + "\t" + Thread.currentThread().getName());
                return "fuck";
            }, threadPool).thenRun (() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("2号任务" + "\t" + Thread.currentThread().getName());
            }).thenRun(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("3号任务" + "\t" + Thread.currentThread().getName());
            }).thenRun(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("4号任务" + "\t" + Thread.currentThread().getName());
            }).get(2L, TimeUnit.SECONDS);
            System.out.println(unused);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }finally {
            threadPool.shutdown();
        }


    }

}
