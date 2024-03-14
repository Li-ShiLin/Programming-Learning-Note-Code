package com.sgg.juc.cas;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicStampedReference;
public class AtomicStampedDemo {
    public static void main(String[] args) {
        // 初始化的Book
        Book javaBook = new Book(1,"javaBook");
        AtomicStampedReference<Book> stampedReference = new AtomicStampedReference<>(javaBook,1);
        System.out.println(stampedReference.getReference()+"\t"+stampedReference.getStamp());
        Book mysqlBook = new Book(2,"mysqlBook");
        boolean b;
        b = stampedReference.compareAndSet(javaBook, mysqlBook, stampedReference.getStamp(), stampedReference.getStamp() + 1);
        System.out.println(b+"\t"+stampedReference.getReference()+"\t"+stampedReference.getStamp());
        b = stampedReference.compareAndSet(mysqlBook, javaBook, stampedReference.getStamp(), stampedReference.getStamp() + 1);
        System.out.println(b+"\t"+stampedReference.getReference()+"\t"+stampedReference.getStamp());
//程序输出：
//            Book(id=1, bookName=javaBook)	1
//            true	Book(id=2, bookName=mysqlBook)	2
//            true	Book(id=1, bookName=javaBook)	3
    }
}

@NoArgsConstructor
@AllArgsConstructor
@Data
class Book
{
    private int id;
    private String bookName;
}
