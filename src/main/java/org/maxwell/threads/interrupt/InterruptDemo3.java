package org.maxwell.threads.interrupt;

/**
 * @description:
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/14 14:33
 */
public class InterruptDemo3 {

    public static void main(String[] args) {

        //static interrupted 返回线程中断状态并清空重置
        System.out.println(Thread.interrupted());
        System.out.println(Thread.interrupted());
        System.out.println("--- 1");
        Thread.currentThread().interrupt();
        System.out.println("--- 2");
        System.out.println(Thread.interrupted());
        System.out.println(Thread.interrupted());

    }
}