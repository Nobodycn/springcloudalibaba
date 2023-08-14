package com.tulingxueyuan.oauth2.service;


import com.tulingxueyuan.oauth2.bean.Permission;

import java.util.List;

/**
 * @author Fox
 */
public interface PermissionService  {

    List<Permission> selectByUserId(Long userId);
}
