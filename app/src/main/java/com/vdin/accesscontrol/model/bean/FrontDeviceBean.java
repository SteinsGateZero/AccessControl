package com.vdin.accesscontrol.model.bean;

/**
 * Created by new1 on 2018/11/
 * 前端设备信息
 */

public class FrontDeviceBean {
    private String name;
    private String img;
    private boolean isOnline;
    private String offline;

    public String getOffline() {
        return offline;
    }

    public void setOffline(String offline) {
        this.offline = offline;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
