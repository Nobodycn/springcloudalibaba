package com.tulingxueyuan.netty.sharehndler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.Random;

public class EchoClientMCHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private Random r = new Random();

    /*读取到网络数据后进行业务处理*/
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("client Accept" + msg.toString(CharsetUtil.UTF_8));
        //ctx.close();
        // 调用ctx.writeAndFlush(in);会自动释放buffer
        // 但是当实现了write方法之后，并没有调用super.write的方法，导致handler链断开，没有继续向后传递，导致buffer没有被释放掉
        // 在此基础上，netty帮忙实现了 SimpleChannelInboundHandler
        // 使用模板模式，实现channelRead0，流程继续调用channelRead，但是前后实现了释放资源的逻辑代码
    }

    /*channel活跃后，做业务处理*/
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf msg = null;
        String request = "Hello"
                + System.getProperty("line.separator");
        int sendCount = r.nextInt(10) + 1;
        for (int i = 0; i < sendCount; i++) {
            msg = Unpooled.buffer(request.length());
            msg.writeBytes(request.getBytes());
            ctx.writeAndFlush(msg);
        }
    }
}
