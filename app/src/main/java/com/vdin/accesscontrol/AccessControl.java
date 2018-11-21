package com.vdin.accesscontrol;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

/**
 * Created by new1 on 2018/10/24.
 * 门禁应用
 */

public class AccessControl extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        context = null;
    }

    public static Context getContext() {
        return context;
    }
}
