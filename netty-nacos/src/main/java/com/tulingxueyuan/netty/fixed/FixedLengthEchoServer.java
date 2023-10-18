package com.tulingxueyuan.netty.fixed;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * 作者：Mark
 * 类说明：基于Netty的服务器
 */

public class FixedLengthEchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(FixedLengthEchoServer.class);

    public static final String RESPONSE = "Welcome to Netty!";
    public static final int PORT = 9996;

    public static void main(String[] args) throws InterruptedException {
        FixedLengthEchoServer fixedLengthEchoServer = new FixedLengthEchoServer();
        System.out.println("服务器即将启动");
        fixedLengthEchoServer.start();
    }

    public void start() throws InterruptedException {
        /*线程组*/
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            /*服务端启动必备*/
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(PORT))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new FixedLengthFrameDecoder(FixedLengthEchoClient.REQUEST.length()));
                            ch.pipeline().addLast(new FixedLengthServerHandler());
                        }
                    });

            /*异步绑定到服务器，sync()会阻塞到完成*/
            ChannelFuture f = b.bind().sync();
            System.out.println("服务器启动完成，等待客户端的连接和数据.....");
            /*阻塞当前线程，直到服务器的ServerChannel被关闭*/
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }
}
