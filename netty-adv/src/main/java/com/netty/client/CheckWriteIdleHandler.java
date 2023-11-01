package com.netty.client;

import io.netty.handler.timeout.IdleStateHandler;

/**
 * @author ：
 * @description ：客户端检测自己的写空闲
 */
public class CheckWriteIdleHandler extends IdleStateHandler {

    public CheckWriteIdleHandler() {
        super(0, 8, 0);
    }
}
