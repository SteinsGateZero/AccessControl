package com.vdin.accesscontrol.model.bean;

import java.util.List;

/**
 * Created by new1 on 2018/11/20.
 * 分组主列表
 */

public class GroupBean {
    private String name;
    private String percent;
    private int num;
    private boolean isSpread = false;
    private List<MemberBean> list;

    public List<MemberBean> getList() {
        return list;
    }

    public void setList(List<MemberBean> list) {
        this.list = list;
    }

    public boolean isSpread() {
        return isSpread;
    }

    public void setSpread(boolean spread) {
        isSpread = spread;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
