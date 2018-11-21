package com.vdin.accesscontrol.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.vdin.accesscontrol.R;
import com.vdin.accesscontrol.base.netbase.NetBaseTitleActivity;
import com.vdin.accesscontrol.contract.SettingContract;
import com.vdin.accesscontrol.eventbus.LogoutEvent;
import com.vdin.accesscontrol.model.SettingModel;
import com.vdin.accesscontrol.presenter.SettingPresenter;
import com.vdin.accesscontrol.ui.loginAndSplash.LoginActivity;
import com.vdin.accesscontrol.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by new1 on 2018/11/14.
 * 设置界面
 */

public class SettingActivity extends NetBaseTitleActivity<SettingModel, SettingContract.ISettingView, SettingPresenter> implements SettingContract.ISettingView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        setBackArrow("设置");
    }

    @Override
    protected SettingPresenter createPresenter() {
        return new SettingPresenter();
    }

    @OnClick({R.id.set_logout, R.id.set_btn_version, R.id.set_btn_support, R.id.set_btn_about})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.set_logout:
                mPresenter.logout();
                break;
            case R.id.set_btn_version:
                break;
            case R.id.set_btn_support:
                break;
            case R.id.set_btn_about:
                break;
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void cancelLoading() {

    }

    @Override
    public void showToast(String message) {
        ToastUtils.showToast(message);
    }

    @Override
    public void logoutIntent() {
        finish();
        startActivity(new Intent(SettingActivity.this, LoginActivity.class));
        EventBus.getDefault().post(new LogoutEvent());
    }
}
