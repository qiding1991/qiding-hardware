package com.qiding.hardware.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qiding.hardware.db.HardwareInfoDb;
import com.qiding.hardware.param.Hardward;

/**
 * @author <qiding@kuaishou.com>
 * Created on 2020-10-22
 */
@RestController
public class HardwareController {

    @Resource
    private HardwareInfoDb infoDb;

    @PostMapping("hardward")
    public Object hardward(@RequestBody Hardward hardward) {
        return infoDb.getHardwareInfo();
    }
}
