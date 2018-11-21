package com.vdin.accesscontrol.utils;

import android.widget.Toast;

import com.vdin.accesscontrol.AccessControl;

public final class ToastUtils {

    private static Toast mToast = Toast.makeText(AccessControl.getContext(), null, Toast.LENGTH_SHORT);

    /**
     * 显示toast
     *
     * @param toastMsg 信息
     */
    public static void showToast(int toastMsg) {
        mToast.setText(toastMsg);
        mToast.show();
    }

    /**
     * 显示toast
     *
     * @param toastMsg 信息
     */
    public static void showToast(String toastMsg) {
        mToast.setText(toastMsg);
        mToast.show();
    }


    public static void destroy() {
        mToast = null;
    }

}
