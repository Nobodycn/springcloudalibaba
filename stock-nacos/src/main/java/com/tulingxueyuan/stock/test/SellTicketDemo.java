package com.tulingxueyuan.stock.test;

public class SellTicketDemo implements Runnable {
    /**
     * 车票
     */
    private int ticket;

    public SellTicketDemo() {
        this.ticket = 1000;
    }

    @Override
    public void run() {
        while (ticket > 0) {
            synchronized (this) {
                if (ticket > 0) {
                    try {
                        // 线程进入暂时的休眠
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 获取到当前正在执行的程序的名称，打印余票
                    System.out.println(Thread.currentThread().getName()
                            + ":正在执行操作，余票:" + ticket--);
                }
            }
            Thread.yield();
        }
    }
}