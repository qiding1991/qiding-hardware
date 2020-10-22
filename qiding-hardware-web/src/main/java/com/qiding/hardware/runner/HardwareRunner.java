package com.qiding.hardware.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.qiding.hardware.HardwareServer;

/**
 * @author <qiding@kuaishou.com>
 * Created on 2020-10-22
 */
@Component
public class HardwareRunner implements ApplicationRunner {

    private HardwareServer hardwareServer;

    public HardwareRunner(HardwareServer hardwareServer) {
        this.hardwareServer = hardwareServer;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
          new Thread(()->hardwareServer.start()).start();
    }
}
