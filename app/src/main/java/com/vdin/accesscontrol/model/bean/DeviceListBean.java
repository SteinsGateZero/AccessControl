package com.vdin.accesscontrol.model.bean;

/**
 * Created by new1 on 2018/11/12.
 * 显示设备列表
 */

public class DeviceListBean {
    private String deviceName;
    private boolean isOnline;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }
}
