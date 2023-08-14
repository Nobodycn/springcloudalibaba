package com.tulingxueyuan.oauth2.service.impl;

import com.tulingxueyuan.oauth2.bean.Permission;
import com.tulingxueyuan.oauth2.bean.User;
import com.tulingxueyuan.oauth2.mapper.PermissionMapper;
import com.tulingxueyuan.oauth2.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    public User getByUsername(String username) {
        return userMapper.getByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user != null) {
            List<Permission> permissions = permissionMapper.selectByUserId(user.getId());

            permissions.forEach(permission -> {
                if (permission != null && !StringUtils.isEmpty(permission.getEnname())) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getEnname());
                    authorities.add(grantedAuthority);
                }
            });
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(), user.getPassword(), authorities);
        } else {
            throw new UsernameNotFoundException("用户名不存在");
        }

    }
}
