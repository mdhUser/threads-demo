package org.maxwell.threads.vol;

/**
 * @description: 多线程并发安全单例模式 DCl
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/27 18:58
 */
public class SafeDoubleCheckSingleton {

    // volatile防止jvm创建对象指令重排序
    private static volatile SafeDoubleCheckSingleton instance;

    private SafeDoubleCheckSingleton() {
    }

    public static SafeDoubleCheckSingleton getInstance() {
        if (instance == null) {
            synchronized (SafeDoubleCheckSingleton.class) {
                if (instance == null) {
                    instance = new SafeDoubleCheckSingleton();
                }
            }
        }
        return instance;
    }

}
