package com.tulingxueyuan.resource.controller;

import com.tulingxueyuan.resource.bean.Content;
import com.tulingxueyuan.resource.dto.ResponseResult;
import com.tulingxueyuan.resource.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Fox
 */
@RestController
@RequestMapping("/contents")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @GetMapping("/")
    public ResponseResult<List<Content>> selectAll() {
        return new ResponseResult<>(HttpStatus.OK.value(), HttpStatus.OK.toString(), contentService.selectAll());
    }

    @GetMapping("/view/{id}")
    public ResponseResult<Content> getById(@PathVariable("id")long id) {
        return new ResponseResult<>(HttpStatus.OK.value(), HttpStatus.OK.toString(), contentService.getById(id));
    }
}
