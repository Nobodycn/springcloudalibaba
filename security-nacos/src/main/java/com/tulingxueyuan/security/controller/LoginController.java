package com.tulingxueyuan.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/tomain")
    public String tomain(){
        return "redirect:/main.html";
    }
}
