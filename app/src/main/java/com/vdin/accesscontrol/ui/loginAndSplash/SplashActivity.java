package com.vdin.accesscontrol.ui.loginAndSplash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.vdin.accesscontrol.R;
import com.vdin.accesscontrol.ui.main.activity.MainActivity;
import com.vdin.accesscontrol.utils.LoginUtils;

/**
 * 闪屏界面
 */
public class SplashActivity extends Activity {
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageView = new ImageView(this);
        setContentView(imageView);
        imageView.setImageResource(R.mipmap.shanping);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        startSplash();
    }

    private void startSplash() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //判断是否登录过
                    if (LoginUtils.getInstance().isLogin()) {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    } else {
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    }
                    finish();
                }
            }
        }).start();
    }

    @Override
    public void onBackPressed() {
        //屏蔽返回按键
    }
}
