package com.tulingxueyuan.oauth2.service.impl;

import com.tulingxueyuan.oauth2.bean.Permission;
import com.tulingxueyuan.oauth2.mapper.PermissionMapper;
import com.tulingxueyuan.oauth2.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Fox
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public List<Permission> selectByUserId(Long userId) {

        return permissionMapper.selectByUserId(userId);
    }
}
