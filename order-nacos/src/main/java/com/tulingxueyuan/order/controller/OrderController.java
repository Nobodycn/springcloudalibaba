package com.tulingxueyuan.order.controller;


import com.tulingxueyuan.order.feign.ProductService;
import com.tulingxueyuan.order.feign.StockFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wangc
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Qualifier("com.tulingxueyuan.order.feign.StockFeignService")
    @Autowired
    StockFeignService stockFeignService;

    @Autowired
    ProductService productService;

    // feign原生注解 @RequestLine
    @RequestMapping("/add")
    public String add() {
        String msg = stockFeignService.reduct();
        String product = getProductById(1);
        return "Add success:" + msg + product;
    }

    // @PathVariable原生注解 @Param
    @RequestMapping("/get/{id}")
    public String getProductById(@PathVariable("id") Integer id) {
        return productService.get(id);
    }

    @RequestMapping("/reduct")
    public String reduct() {
        return stockFeignService.reduct();
    }
}
