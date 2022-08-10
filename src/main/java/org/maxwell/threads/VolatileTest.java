package org.maxwell.threads;

/**
 * @description:
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/8 22:14
 */
public class VolatileTest {

    private volatile static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {

       new  Thread(()->{
           while (flag){}
           System.out.println("退出线程："+Thread.currentThread().getName());
       },"thread1").start();

       Thread.sleep(1000);
       new Thread(()->{
           flag=false;
           System.out.println("线程%s已修改变量状态".formatted(Thread.currentThread().getName()));
       },"thread2").start();
    }

}
