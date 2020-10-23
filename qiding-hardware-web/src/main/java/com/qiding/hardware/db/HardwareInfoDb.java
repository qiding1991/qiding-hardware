package com.qiding.hardware.db;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author <qiding@kuaishou.com>
 * Created on 2020-10-22
 */
public class HardwareInfoDb implements Consumer<byte[]> {
    List<byte[]> hardwareInfo = new ArrayList<>();

    public List<byte[]> getHardwareInfo() {
        return hardwareInfo;
    }

    @Override
    public void accept(byte[] data) {
        hardwareInfo.add(data);
    }


    public void clean(){
        hardwareInfo.clear();
    }
}
