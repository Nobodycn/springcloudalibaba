package com.tulingxueyuan.netty.linebase;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;

import java.net.InetSocketAddress;

public class LineBaseEchoServer {
    public static final int PORT = 9998;

    public static void main(String[] args) throws InterruptedException {
        LineBaseEchoServer lineBaseEchoServer = new LineBaseEchoServer();
        lineBaseEchoServer.start();
    }

    private void start() throws InterruptedException {
        final LineBaseServerHandler handler = new LineBaseServerHandler();
        // 1 new EventLoopGroup
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group).channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(PORT))
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                            channel.pipeline().addLast(new LineBaseServerHandler());
                        }
                    });
            ChannelFuture future = bootstrap.bind().sync();
            System.out.println("服务器启动完成，等待客户端的连接和数据.....");
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();/*优雅关闭线程组*/
        }
    }
}
