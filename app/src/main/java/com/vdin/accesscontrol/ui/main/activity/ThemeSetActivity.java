package com.vdin.accesscontrol.ui.main.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.vdin.accesscontrol.R;
import com.vdin.accesscontrol.base.CommonTitleActivity;

/**
 * Created by new1 on 2018/11/12.
 * 主题设置查看界面
 */

public class ThemeSetActivity extends CommonTitleActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themeset);
        init();
    }

    private void init() {
        setBackArrow("查看主题设置");
    }
}
