package com.tulingxueyuan.stock.test;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    public static void main(String[] args) {
        Thread parkThread = new Thread(new ParkThread());
        parkThread.start();
        System.out.println("唤醒parkThread");
        LockSupport.unpark(parkThread);
    }

    static class ParkThread implements Runnable {

        @Override
        public void run() {
            System.out.println("ParkThread开始执行");
            LockSupport.park();
            System.out.println("ParkThread执行完成");
        }
    }
}