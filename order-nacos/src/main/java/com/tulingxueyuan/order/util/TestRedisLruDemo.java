package com.tulingxueyuan.order.util;
 
import java.util.Random;
import java.util.concurrent.TimeUnit;
 
/**
 * <p>
 *      测试LRU
 * </p>
 *
 * @Author: Liziba
 * @Date: 2021/9/22 22:51
 */
public class TestRedisLruDemo {
 
    public static void main(String[] args) throws InterruptedException {
 
        RedisLruDemo demo = new RedisLruDemo(10);
        // 先加入10个key，此时cache达到容量，下次加入会淘汰key
        for (int i = 0; i < 10; i++) {
            demo.set(i + "", i);
        }
        // 随机访问前十个key，这样可以保证下次加入时随机淘汰
        for (int i = 0; i < 20; i++) {
            int nextInt = new Random().nextInt(10);
            TimeUnit.SECONDS.sleep(1);
            demo.get(nextInt + "");
        }
        // 再次添加5个key，此时每次添加都会触发淘汰
        for (int i = 10; i < 15; i++) {
            demo.set(i + "", i);
        }
 
        System.out.println("-------------------------------------------");
        demo.getAll().forEach( redisHead -> System.out.println("剩余 -> " + "lru : " + redisHead.getLru() + " body : " + redisHead.getBody()));
    }
 
}