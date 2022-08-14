package org.maxwell.threads.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @description: 计算速度的选用
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/11 20:44
 */
public class CompletableFutureAPIDemo3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        CompletableFuture<String> playA = CompletableFuture.supplyAsync(() -> {
            System.out.println("A come in");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "playA";
        });

        CompletableFuture<String> playB = CompletableFuture.supplyAsync(() -> {
            System.out.println("B come in");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "playB";
        });

        //比较速度选择
        CompletableFuture<String> result = playA.applyToEither(playB, f -> f + " is winner");

        System.out.println(Thread.currentThread().getName() + "\t --------- result:" + result.join());

    }

}
