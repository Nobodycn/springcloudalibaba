package com.tulingxueyuan.order.feign;

import org.springframework.stereotype.Component;

@Component
public class StockFeignFallBack implements StockFeignService{
    @Override
    public String reduct() {
        return "降级处理";
    }
}
