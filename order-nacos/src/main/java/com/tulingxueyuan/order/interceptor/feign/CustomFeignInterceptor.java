package com.tulingxueyuan.order.interceptor.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 */
public class CustomFeignInterceptor implements RequestInterceptor {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void apply(RequestTemplate requestTemplate) {

        requestTemplate.header("name", "xxx");
        requestTemplate.header("id", "111");
        requestTemplate.uri("/9");
        logger.info("feign拦截器!");
    }
}
