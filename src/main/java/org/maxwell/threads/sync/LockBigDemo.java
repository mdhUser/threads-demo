package org.maxwell.threads.sync;

import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/9/4 20:17
 */
@Slf4j
public class LockBigDemo {


    public static void main(String[] args) {

        Object lock = new Object();


        //jvm会粗化成一个synchronized代码块
        new Thread(()->{
           synchronized (lock){
               log.info("get 1");
           }synchronized (lock){
               log.info("get 2");
           }synchronized (lock){
               log.info("get 3");
           }synchronized (lock){
               log.info("get 4");
           }synchronized (lock){
               log.info("get 5");
           }synchronized (lock){
               log.info("get 6");
           }synchronized (lock){
               log.info("get 7");
           }
        });


    }

}
