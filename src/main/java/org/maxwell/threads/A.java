package org.maxwell.threads;

/**
 * @description:
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/10 22:02
 */
public class A {

    static {
        System.out.println("1");
    }

    {
        System.out.println("Ai");
    }

    public A() {
        System.out.println("a");
    }


    public static void main(String[] args) {
        A a = new B();
        a = new B();
    }

    static class B extends A {

        static {
            System.out.println("2");
        }

        {
            System.out.println("Bi");
        }

        public B() {
            System.out.println("b");
        }
    }

}
