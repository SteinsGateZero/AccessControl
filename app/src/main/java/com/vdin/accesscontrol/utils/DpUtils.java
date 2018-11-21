package com.vdin.accesscontrol.utils;

import com.vdin.accesscontrol.AccessControl;

public class DpUtils {

    public static int dpToPx(float dp) {
        float density = AccessControl.getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f * (dp >= 0 ? 1 : -1));
    }

    public static int spToPx(float spValue) {
        float fontScale = AccessControl.getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

}
