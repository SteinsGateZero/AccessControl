package com.vdin.accesscontrol.ui.main.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.vdin.accesscontrol.R;
import com.vdin.accesscontrol.base.CommonTitleActivity;

/**
 * Created by new1 on 2018/11/12.
 * 本机网络界面
 */

public class LocalNetActivity extends CommonTitleActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localnet);
        init();
    }

    private void init() {
        setBackArrow("本机网络设置");
    }
}
