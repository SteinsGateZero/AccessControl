package com.vdin.accesscontrol.utils;

import com.vdin.accesscontrol.eventbus.CountDownEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by new1 on 2018/11/8.
 * 倒计时事件
 */

public class CountDownUtils {
    private static Thread threadForget;
    private static Thread threadSignUp;

    private CountDownUtils() {
    }

    public static void start(boolean isSignUp) {
        if (isSignUp) {
            threadSignUp = initThread(threadSignUp, isSignUp);
        } else {
            threadForget = initThread(threadForget, isSignUp);
        }

    }

    private static Thread initThread(Thread thread, final boolean isSignUp) {
        if (thread == null || !thread.isAlive()) {
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    int i = 59;
                    CountDownEvent countDown = new CountDownEvent();
                    countDown.setSignUp(isSignUp);
                    while (i >= 0) {
                        countDown.setCount(i);
                        EventBus.getDefault().post(countDown);
                        i--;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            thread1.start();
            return thread1;
        }
        return thread;
    }
}
