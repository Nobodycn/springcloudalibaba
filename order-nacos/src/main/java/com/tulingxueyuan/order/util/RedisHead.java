package com.tulingxueyuan.order.util;
 
/**
 * <p>
 *      Redis对象头
 * </p>
 *
 * @Author: Liziba
 * @Date: 2021/9/22 22:40
 */
public class RedisHead {
 
    /** 时间 */
    private Long lru;
    /** 具体数据 */
    private Object body;
 
    public RedisHead setLru(Long lru) {
        this.lru = lru;
        return this;
    }
 
    public RedisHead setBody(Object body) {
        this.body = body;
        return this;
    }
 
 
    public Long getLru() {
        return lru;
    }
 
    public Object getBody() {
        return body;
    }
 
}