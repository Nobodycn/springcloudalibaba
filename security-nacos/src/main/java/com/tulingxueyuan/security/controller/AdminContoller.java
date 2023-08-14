package com.tulingxueyuan.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wangc
 */
@RestController
@RequestMapping("/admin")
public class AdminContoller {

    @RequestMapping("/demo")
    public String demo(){
        String userName = getUsername();
        return "Spring security demo:" + userName;
    }

    @RequestMapping("/index")
    public String index(){
        String userName = getUsername();
        return "Spring security index:" + userName;
    }
    private String getUsername(){
        // 获取当前登录的用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!authentication.isAuthenticated()){
            return null;
        }
        Object principal = authentication.getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username =((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }
}
