package com.tulingxueyuan.security.config;

import com.tulingxueyuan.security.handle.MyExpiredSessionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Administrator
 */
@Configuration
public class WebSecurityConfig2 extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailServiceImpl userDetailService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 表单登录
        http.formLogin();

        // 配置了上面的表单登录，就需要在这里显示配置权限控制
        http.authorizeRequests()
                .antMatchers("/admin/demo").hasAnyAuthority("user")
                .antMatchers("/admin/index").hasAnyAuthority("user2")
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
