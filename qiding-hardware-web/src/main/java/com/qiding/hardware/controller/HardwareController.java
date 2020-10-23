package com.qiding.hardware.controller;

import com.qiding.hardware.db.HardwareInfoDb;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author <qiding@kuaishou.com>
 * Created on 2020-10-22
 */
@RestController
public class HardwareController {

    @Resource
    private HardwareInfoDb infoDb;

    @GetMapping("hardware")
    public void hardware(HttpServletResponse response) throws IOException {

        System.out.println("报文个数="+infoDb.getHardwareInfo().size());
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;   filename=RTCM3.bin");
        try (OutputStream outputStream = response.getOutputStream()) {
            infoDb.getHardwareInfo().stream()
                    .forEach(bytes -> {
                        try {
                            outputStream.write(bytes);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            outputStream.flush();
            response.flushBuffer();
        }
    }

    @PostMapping("reset")
    public String reset(){
        infoDb.clean();
        return "clean success";
    }

}
