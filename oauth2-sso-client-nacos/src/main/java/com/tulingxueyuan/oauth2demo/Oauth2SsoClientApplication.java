package com.tulingxueyuan.oauth2demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableOAuth2Sso
@EnableDiscoveryClient
public class Oauth2SsoClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(Oauth2SsoClientApplication.class, args);
    }
}
