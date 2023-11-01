package com.netty.server.asyncpro;

import com.netty.vo.MyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ：
 * @description ：消息转任务处理器的缺省实现
 */
public class DefaultTaskProcessor implements ITaskProcessor {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultTaskProcessor.class);

    @Override
    public Runnable execAsyncTask(MyMessage msg) {
        Runnable task = () -> LOG.info("DefaultTaskProcessor模拟任务处理：" + msg.getBody());
        return task;
    }
}
