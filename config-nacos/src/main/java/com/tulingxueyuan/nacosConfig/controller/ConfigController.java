package com.tulingxueyuan.nacosConfig.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wangc
 */
@RestController
@RequestMapping("/config")
class ConfigController {
    @Value("${server.port}")
    String port;
}
