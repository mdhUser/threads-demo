package org.maxwell.threads.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @description:
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/11 20:44
 */
public class CompletableFutureAPIDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "fuck";
        });

        //指定时间获取value否则抛出异常Timeout
//        System.out.println(completableFuture.get(4, TimeUnit.SECONDS));
        //立即获取result如果没有处理完成则返回absent
//        System.out.println(completableFuture.getNow("xxx"));

        TimeUnit.SECONDS.sleep(2);
        System.out.println(completableFuture.complete("xxxx")+"\t"+completableFuture.join());

    }

}
