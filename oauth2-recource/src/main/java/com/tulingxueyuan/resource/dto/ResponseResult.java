package com.tulingxueyuan.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Fox
 */
@Data
@AllArgsConstructor
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int state;

    private String message;

    private T data;


}
