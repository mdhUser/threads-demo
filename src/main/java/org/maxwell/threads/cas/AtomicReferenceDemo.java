package org.maxwell.threads.cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @description: 原子引用类demo
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/28 12:50
 */
public class AtomicReferenceDemo {

    public static void main(String[] args) {

        AtomicReference<User> atomicReference = new AtomicReference<>();
        User user = new User("张三");
        User user1 = new User("李四");

        atomicReference.set(user);

        System.out.println(atomicReference.compareAndSet(user, user1));
        System.out.println(atomicReference.compareAndSet(user, user1));

    }


}

class User {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String name) {
        this.name = name;
    }
}