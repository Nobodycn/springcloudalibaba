package com.tulingxueyuan.sharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Wangc
 */
@SpringBootApplication
//@EnableDiscoveryClient
@MapperScan("com.tulingxueyuan.sharding.mapper")
public class ShardingDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShardingDemoApplication.class, args);
    }
}
