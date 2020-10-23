package com.qiding.hardware.config;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qiding.hardware.HardwareServer;
import com.qiding.hardware.db.HardwareInfoDb;
import com.qiding.hardware.handler.MyChannelHandler;
import com.qiding.hardware.handler.MyChannelInitializer;

import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author <qiding@kuaishou.com>
 * Created on 2020-10-22
 */
@Configuration
public class QidingHardwareConfig {

    @Bean
    HardwareInfoDb dataStore(){
        return new HardwareInfoDb();
    }

    @Bean
    public  List<ChannelHandler> channelHandler(HardwareInfoDb dataStore) {
        //return Stream.of(new StringDecoder(), new StringEncoder(), new MyChannelHandler(dataStore)).collect(Collectors.toList());
        return Stream.of( new MyChannelHandler(dataStore)).collect(Collectors.toList());
    }

    @Bean
    public MyChannelInitializer initializer(List<ChannelHandler> channelHandlerList,
            @Value("${qiding.hardware.netty.delimiter}") String delimiter) {
        return new MyChannelInitializer(channelHandlerList, delimiter);
    }

    @Bean
    public  HardwareServer hardwareServer(MyChannelInitializer initializer,
            @Value("${qiding.hardware.netty.port}") Integer port) {
        return new HardwareServer(port, initializer);
    }

}
