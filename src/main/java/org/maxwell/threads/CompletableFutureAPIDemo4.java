package org.maxwell.threads;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @description: 计算速度的选用
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/11 20:44
 */
public class CompletableFutureAPIDemo4 {

    public static void main(String[] args) {

        CompletableFuture<Integer> playA = CompletableFuture.supplyAsync(() -> {
            System.out.println("A come in");
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 10;
        });

        CompletableFuture<Integer> playB = CompletableFuture.supplyAsync(() -> {
            System.out.println("B come in");
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 20;
        });

        //合并函数
        System.out.println(playA.thenCombine(playB, Integer::sum).join());


    }


}
