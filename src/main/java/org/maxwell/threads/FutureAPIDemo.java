package org.maxwell.threads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/9 21:07
 */
public class FutureAPIDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ----come in");
            TimeUnit.SECONDS.sleep(5);
            return "task over";
        });

        Thread t1 = new Thread(futureTask, "t1");
        t1.start();

//        System.out.println(futureTask.get()); 会导致线程阻塞效率十分低下

        System.out.println(Thread.currentThread().getName() + "\t ===忙其他任务了");
        while (true){
            if (futureTask.isDone()){
                System.out.println(futureTask.get());
                break;
            }else {
                //休眠半秒
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println("正在处理中~~~~");
            }
        }

    }

}
