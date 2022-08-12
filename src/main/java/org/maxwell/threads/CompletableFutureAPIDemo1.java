package org.maxwell.threads;

import java.util.concurrent.*;

/**
 * @description:
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/11 20:44
 */
public class CompletableFutureAPIDemo1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        //handle可处理分支异常，thenApply直接抛出中断线程
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("111");
                    return 1;
                }, threadPool).handle((f, e) -> {
                    int i = 10 / 0;
                    System.out.println("222");
                    return f + 1;
                }).handle((f, e) -> {
                    if (e != null) {
                        System.out.println(e.getMessage());
                        f = 2;
                    }
                    System.out.println("333");
                    return f + 1;
                }).whenComplete((v, e) -> System.out.println("计算结果为===" + v))
                .exceptionally(e -> {
                    System.out.println(e.getMessage());
                    return null;
                });

        System.out.println(Thread.currentThread().getName() + "线程去处理其他业务~~~");

        threadPool.shutdown();
    }

}
