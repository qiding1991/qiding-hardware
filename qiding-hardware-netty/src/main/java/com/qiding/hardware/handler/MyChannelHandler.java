package com.qiding.hardware.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.function.Consumer;

/**
 * @author <qiding@kuaishou.com>
 * Created on 2020-10-22
 */
@ChannelHandler.Sharable
public class MyChannelHandler extends ChannelInboundHandlerAdapter {

    private Consumer<byte[]> consumer;

    public MyChannelHandler(Consumer<byte[]> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
         byte[]bytes=ByteBufUtil.getBytes((ByteBuf) msg);
         System.out.println("收到数据，长度="+bytes.length);
         consumer.accept(bytes);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
