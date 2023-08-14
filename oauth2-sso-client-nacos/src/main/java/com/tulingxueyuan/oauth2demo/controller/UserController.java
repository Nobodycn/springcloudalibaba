package com.tulingxueyuan.oauth2demo.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/getCurrentUser")
    public String getCurrentUser(Authentication authentication){
        return JSON.toJSONString(authentication.getName());
    }
}
