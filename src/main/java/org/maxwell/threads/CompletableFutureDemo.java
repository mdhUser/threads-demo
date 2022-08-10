package org.maxwell.threads;

import java.util.concurrent.*;

/**
 * @description:
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/8 23:50
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " ---come in");
            int result = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (result > 5) {
                int i = 10 / 0;
            }
            System.out.println("1s之后输出结果：" + result);
            return result;
        }, threadPool).whenComplete((v, e) -> {
                System.out.println(" ---- 计算完成，更新系统UpdateValue:" + v);
        }).exceptionally(e -> {
            System.out.println("异常情况:" + e.getCause() + "\t " + e.getMessage());
            return null;
        });

        System.out.println(Thread.currentThread().getName() + "线程先去忙其他任务");
//        TimeUnit.SECONDS.sleep(3);
        threadPool.shutdown();

    }

    private static void futureBlock() throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " ---come in");
            int result = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("1s之后输出结果：" + result);
            return result;
        });
        System.out.println(Thread.currentThread().getName() + "线程先去忙其他任务");

        System.out.println(completableFuture.get());
    }

    private static void future1() throws InterruptedException, ExecutionException {
        //使用供给型方法有返回值，可自定义线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return "hello supplyAysnc";
        }, threadPool);
        System.out.println(completableFuture.get());
        threadPool.shutdown();
    }

    private static void runAsync() throws InterruptedException, ExecutionException {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println(completableFuture.get());
    }

}
