package com.vdin.accesscontrol.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.vdin.accesscontrol.AccessControl;

/**
 * Created by new1 on 2018/10/24.
 */

public final class LoginUtils {

    private final static LoginUtils utils = new LoginUtils();

    private LoginUtils() {
    }

    public static LoginUtils getInstance() {
        return utils;
    }

    /**
     * 登录
     */
    public void login() {
        SharedPreferences sharedPreferences = AccessControl.getContext().getSharedPreferences("isLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLogin", true);
        editor.apply();
    }

    /**
     * 登出
     */
    public void loginOut() {
        SharedPreferences sharedPreferences = AccessControl.getContext().getSharedPreferences("isLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLogin", false);
        editor.apply();
    }

    /**
     * @return 是否登录
     */
    public boolean isLogin() {
        SharedPreferences sharedPreferences = AccessControl.getContext().getSharedPreferences("isLogin", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("isLogin", false);
    }
}
