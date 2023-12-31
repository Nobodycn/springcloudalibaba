package com.tulingxueyuan.oauth2.service;

import com.tulingxueyuan.oauth2.bean.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Fox
 */
public interface UserService extends UserDetailsService {

    User getByUsername(String username);
}
