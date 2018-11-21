package com.vdin.accesscontrol.eventbus;

/**
 * Created by new1 on 2018/11/8.
 * 倒计时事件
 */

public class CountDownEvent {
    private int count;
    private boolean isSignUp;

    public boolean isSignUp() {
        return isSignUp;
    }

    public void setSignUp(boolean signUp) {
        isSignUp = signUp;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
