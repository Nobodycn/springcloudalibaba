package com.tulingxueyuan.order.config;

import com.tulingxueyuan.order.interceptor.feign.CustomFeignInterceptor;
import feign.Contract;
import feign.Logger;
import feign.Request;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * 全局：当使用@Configuration,会将配置作用在所有的服务提供方
 * 局部：如果只针对某一个服务进行配置，就不加configuration
 */
//@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * 修改契约配置，支持feign原生注解
     *
     * @return
     */
    /*@Bean
    public Contract feignContract(){
        return new Contract.Default();
    }*/

    /**
     * 超时配置
     */
    /*@Bean
    public Request.Options options() {
        return new Request.Options(10, TimeUnit.SECONDS, 60, TimeUnit.SECONDS, true);
    }*/

    /**
     * 自定义拦截器
     */
    /* @Bean
    public CustomFeignInterceptor customFeignInterceptor(){
        return new CustomFeignInterceptor();
    }*/
}
