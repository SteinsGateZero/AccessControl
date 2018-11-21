package com.vdin.accesscontrol.ui.main.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vdin.accesscontrol.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by new1 on 2018/11/12.
 * 全屏展示前端设备视图
 */

public class FullScreenDeviceActivity extends AppCompatActivity {

    @BindView(R.id.full_img)
    ImageView fullImg;
    @BindView(R.id.titleBar_tv)
    TextView titleBarTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_device);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        Glide.with(this).load(getIntent().getStringExtra("img")).into(fullImg);
        titleBarTv.setText(getIntent().getStringExtra("name"));
    }

    @OnClick(R.id.titleBar_tv)
    public void onViewClicked() {
        finish();
    }
}
