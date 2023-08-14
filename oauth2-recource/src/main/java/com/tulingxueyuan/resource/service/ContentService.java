package com.tulingxueyuan.resource.service;



import com.tulingxueyuan.resource.bean.Content;

import java.util.List;

public interface ContentService {

    Content getById(Long id);

    List<Content> selectAll();


}
