package com.vdin.accesscontrol.model;

import com.vdin.accesscontrol.contract.SettingContract;
import com.vdin.accesscontrol.presenter.SettingPresenter;
import com.vdin.accesscontrol.utils.LoginUtils;

/**
 * Created by new1 on 2018/11/14.
 * 设置界面model层
 */

public class SettingModel implements SettingContract.ISettingModel {
    private SettingPresenter presenter;

    public SettingModel(SettingPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void logout() {
        LoginUtils.getInstance().loginOut();
        presenter.onSuccess();
    }
}
