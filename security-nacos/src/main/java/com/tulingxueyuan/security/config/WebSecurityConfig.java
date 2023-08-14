package com.tulingxueyuan.security.config;

import com.tulingxueyuan.security.handle.MyAuthenticationFailHandler;
import com.tulingxueyuan.security.handle.MyAuthenticationSuccessHandler;
import com.tulingxueyuan.security.handle.MyExpiredSessionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Administrator
 */
//@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailServiceImpl userDetailService;

    @Bean
    PasswordEncoder passwordEncoder() {
        //return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
        //auth.inMemoryAuthentication()
        //        .withUser("fox2")
        //        .password(passwordEncoder().encode("123"))
        //        .authorities("admin");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 表单登录
        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/user/login")
                .defaultSuccessUrl("/main.html")
                .failureUrl("/error.html");
        //.successHandler(new MyAuthenticationSuccessHandler("/main.html"))
        //.failureHandler(new MyAuthenticationFailHandler("/error.html"));

        // 配置了上面的表单登录，就需要在这里显示配置权限控制
        http.authorizeRequests()
                .antMatchers("/session/invalid", "/user/login", "/login.html", "/error.html").permitAll()
                .anyRequest().authenticated()
                // 需要关闭跨域才能访问成功
                .and().csrf().disable();

        http.sessionManagement()
                //.invalidSessionUrl("/session/invalid")
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .expiredSessionStrategy(new MyExpiredSessionStrategy());
    }
}
