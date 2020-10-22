package com.qiding.hardware;


import com.qiding.hardware.handler.MyChannelInitializer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

/**
 * Hello world!
 */
public class HardwareServer {

    private Integer port;

    private MyChannelInitializer myChannelInitializer;

    public HardwareServer(Integer port, MyChannelInitializer myChannelInitializer) {
        this.port = port;
        this.myChannelInitializer = myChannelInitializer;
    }

    public HardwareServer(Integer port) {
        this.port = port;
    }

    public void start() {
        //调度线程
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(3);
        //启动器
        ServerBootstrap bootstrap = new ServerBootstrap();
        //选择线程组
        try {
            bootstrap.group(bossGroup, workerGroup)
                    //绑定 主线程channel
                    .channel(NioServerSocketChannel.class)
                    .childHandler(myChannelInitializer)
                    //子线程处理器
                    //                    .childHandler(new ChannelInitializer<SocketChannel>() {
                    //                        @Override
                    //                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                    //                            socketChannel.pipeline().addLast(new MyChannelHandler());
                    //                        }
                    //                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    //https://www.cnblogs.com/googlemeoften/p/6082785.html
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture future = bootstrap.bind(port).sync();
            future.channel().closeFuture().sync();

        } catch (Exception e) {
            //TODO添加日志
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }


    }


    public static void main(String[] args) throws InterruptedException {
        new HardwareServer(9999).start();
        System.out.println("Hello World!");
    }
}
