package com.qiding.hardware.db;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author <qiding@kuaishou.com>
 * Created on 2020-10-22
 */
public class HardwareInfoDb implements Consumer<String> {
    List<String> hardwareInfo=new ArrayList<>();

    public List<String> getHardwareInfo() {
        return hardwareInfo;
    }

    @Override
    public void accept(String data) {
        hardwareInfo.add(data);
    }
}
