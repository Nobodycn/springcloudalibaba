package com.tulingxueyuan.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "stock-service", path = "/stock", fallback = StockFeignFallBack.class)
public interface StockFeignService {

    @RequestMapping("/reduct")
    String reduct();
}
