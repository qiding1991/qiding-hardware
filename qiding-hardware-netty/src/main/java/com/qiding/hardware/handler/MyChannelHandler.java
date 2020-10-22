package com.qiding.hardware.handler;

import java.util.function.Consumer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author <qiding@kuaishou.com>
 * Created on 2020-10-22
 */
public class MyChannelHandler extends ChannelInboundHandlerAdapter {

    private Consumer<String> consumer;

    public MyChannelHandler(Consumer<String> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String realMsg=(String)msg;
        System.out.println(realMsg);
        consumer.accept(realMsg);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
