package com.tulingxueyuan.order.util;
 
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
 
/**
 * <p>
 * Redis中LRU算法的实现demo
 * </p>
 *
 * @Author: Liziba
 * @Date: 2021/9/22 22:36
 */
public class RedisLruDemo {
 
    /**
     * 缓存容器
     */
    private ConcurrentHashMap<String, RedisHead> cache;
    /**
     * 初始化大小
     */
    private int initialCapacity;
 
    public RedisLruDemo(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        this.cache = new ConcurrentHashMap<>(initialCapacity);
        ;
    }
 
    /**
     * 设置key/value 设置的时候更新LRU
     *
     * @param key
     * @param body
     */
    public void set(String key, Object body) {
        // 触发LRU淘汰
        synchronized (RedisLruDemo.class) {
            if (!cache.containsKey(key) && cache.size() >= initialCapacity) {
                this.flushLruKey();
            }
        }
        RedisHead obj = this.getRedisHead().setBody(body).setLru(System.currentTimeMillis());
        cache.put(key, obj);
    }
 
 
    /**
     * 获取key，存在则更新LRU
     *
     * @param key
     * @return
     */
    public Object get(String key) {
 
        RedisHead result = null;
        if (cache.containsKey(key)) {
            result = cache.get(key);
            result.setLru(System.currentTimeMillis());
        }
 
        return result;
    }
 
 
    /**
     * 清除LRU key
     */
    private void flushLruKey() {
 
        List<String> sortData = cache.keySet()
                .stream()
                .sorted(Comparator.comparing(key -> cache.get(key).getLru()))
                .collect(Collectors.toList());
        String removeKey = sortData.get(0);
        System.out.println( "淘汰 -> " + "lru : " + cache.get(removeKey).getLru() + " body : " + cache.get(removeKey).getBody());
        cache.remove(removeKey);
        if (cache.size() >= initialCapacity) {
            this.flushLruKey();
        }
        return;
    }
 
 
    /**
     *  获取所有数据测试用
     *
     * @return
     */
    public List<RedisHead> getAll() {
         return cache.keySet().stream().map(key -> cache.get(key)).collect(Collectors.toList());
    }
 
 
    private RedisHead getRedisHead() {
        return new RedisHead();
    }
 
}