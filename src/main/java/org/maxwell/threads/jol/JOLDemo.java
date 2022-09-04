package org.maxwell.threads.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * @description:
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/9/4 14:47
 */

class Customer {
    int id;
    boolean flag;
}

public class JOLDemo {

    public static void main(String[] args) {
        //Thread.currentThread()
//        System.out.println(VM.current().details());
        //所有的对象分配的内存都是8的整数倍字节
//        System.out.println(VM.current().objectAlignment());


        Object o = new Object();
        Customer customer = new Customer();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

    }


}
