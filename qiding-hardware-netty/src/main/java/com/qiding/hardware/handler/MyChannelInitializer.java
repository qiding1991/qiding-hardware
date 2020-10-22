package com.qiding.hardware.handler;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author <qiding@kuaishou.com>
 * Created on 2020-10-22
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    private List<ChannelHandler> channelHandler;
    private String delimiterStr;

    public MyChannelInitializer(List<ChannelHandler> channelHandler, String delimiterStr) {
        this.channelHandler = channelHandler;
        this.delimiterStr = delimiterStr;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline=ch.pipeline();
        ByteBuf delimiter= Unpooled.copiedBuffer(delimiterStr.getBytes());
        DelimiterBasedFrameDecoder decoder=new DelimiterBasedFrameDecoder(10000,delimiter);
        pipeline.addLast("frame",decoder);

        channelHandler.forEach(handler->pipeline.addLast(handler));

//
//        pipeline.addLast("decoder",new StringDecoder());
//        pipeline.addLast("encoder",new StringEncoder());
//        pipeline.addLast("channelHandler",new MyChannelHandler());
    }
}
