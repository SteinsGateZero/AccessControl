package com.vdin.accesscontrol.model.bean;

/**
 * Created by new1 on 2018/11/5.
 * 门禁
 */

public class DoorListBean {
    private String name;
    private boolean isOnline;

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
}
