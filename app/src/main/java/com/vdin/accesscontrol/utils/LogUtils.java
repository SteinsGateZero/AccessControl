package com.vdin.accesscontrol.utils;

import android.util.Log;

import com.vdin.accesscontrol.BuildConfig;

/**
 * Created by new1 on 2018/10/25.
 */

public final class LogUtils {

    private final static LogUtils utils = new LogUtils();

    public LogUtils eLog(String tag, String msg) {
        if (!BuildConfig.DEBUG) return this;
        Log.e(tag, msg);
        return this;
    }

    public LogUtils vLog(String tag, String msg) {
        if (!BuildConfig.DEBUG) return this;
        Log.v(tag, msg);
        return this;
    }

    public LogUtils dLog(String tag, String msg) {
        if (!BuildConfig.DEBUG) return this;
        Log.d(tag, msg);
        return this;
    }

    public LogUtils iLog(String tag, String msg) {
        if (!BuildConfig.DEBUG) return this;
        Log.i(tag, msg);
        return this;
    }

    private LogUtils() {
    }

    public static LogUtils getLog() {
        return utils;
    }

}
