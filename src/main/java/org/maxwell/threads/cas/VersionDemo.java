package org.maxwell.threads.cas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @description: 版本号比较替换demo
 * @author: maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/8/28 13:28
 */
@Slf4j
public class VersionDemo {

    public static void main(String[] args) {
        Book javaBook = new Book(1, "java");
        Book mysqlBook = new Book(2, "mysql");

        AtomicStampedReference<Book> stampedReference = new AtomicStampedReference<>(javaBook, 1);
        log.info(stampedReference.getReference() + "\t" + stampedReference.getStamp());
        //替换时检查版本号确定中间过程中没有被其他现场操作过，后对版本号+1
        boolean b = stampedReference.compareAndSet(javaBook, mysqlBook, stampedReference.getStamp(), stampedReference.getStamp() + 1);
        log.info(String.valueOf(b));

    }

}

@Data
@AllArgsConstructor
class Book {
    private int id;
    private String bookName;
}