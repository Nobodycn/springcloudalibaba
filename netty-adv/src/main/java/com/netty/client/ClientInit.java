package com.netty.client;

import com.netty.kryocodec.KryoDecoder;
import com.netty.kryocodec.KryoEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

/**
 * @author Administrator
 */
public class ClientInit extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // add log output
        ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
        // check write idle
        ch.pipeline().addLast(new CheckWriteIdleHandler());

        // 粘包半包
        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(65535,
                0, 2, 0,
                2));

        ch.pipeline().addLast(new LengthFieldPrepender(2));

        ch.pipeline().addLast(new KryoDecoder());
        ch.pipeline().addLast(new KryoEncoder());

        ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
        ch.pipeline().addLast(new LoginAuthReqHandler());

        /*连接读空闲检测*/
        ch.pipeline().addLast(new ReadTimeoutHandler(15));
        /*向服务器发出心跳请求*/
        ch.pipeline().addLast(new HearBeatReqHandler());

        ch.pipeline().addLast(new ClientBusiHandler());
    }
}
