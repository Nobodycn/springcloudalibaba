package com.tulingxueyuan.stock.test;

public class SellTicketMain {
    public static void main(String[] args) {
        SellTicketDemo demo = new SellTicketDemo();

        Thread thread1 = new Thread(demo, "thread1");
        Thread thread2 = new Thread(demo, "thread2");
        Thread thread3 = new Thread(demo, "thread3");
        Thread thread4 = new Thread(demo, "thread4");
        //priority优先级默认是5，最低1，最高10
        thread1.setPriority(Thread.MAX_PRIORITY);
        thread2.setPriority(Thread.MAX_PRIORITY);
        thread3.setPriority(Thread.MIN_PRIORITY);
        thread4.setPriority(Thread.MIN_PRIORITY);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
