package org.maxwell.threads.completable_future;

import java.util.concurrent.*;

/**
 * @description:
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/11 20:44
 */
public class CompletableFutureAPIDemo2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        CompletableFuture.supplyAsync(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("111");
                    return 1;
                }).thenApply(f -> f + 1)
                .thenApply(f -> f + 1)
                .thenAccept(System.out::println).join();


    }

}
