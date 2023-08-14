package com.tulingxueyuan.resource.service.impl;

import com.tulingxueyuan.resource.bean.Content;
import com.tulingxueyuan.resource.mapper.ContentMapper;
import com.tulingxueyuan.resource.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Fox
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;
    @Override
    public Content getById(Long id) {
        return contentMapper.getById(id);
    }

    @Override
    public List<Content> selectAll() {
        return contentMapper.selectAll();
    }
}
