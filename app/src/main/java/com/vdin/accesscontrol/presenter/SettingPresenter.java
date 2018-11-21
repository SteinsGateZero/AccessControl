package com.vdin.accesscontrol.presenter;

import com.vdin.accesscontrol.base.netbase.NetBasePresenter;
import com.vdin.accesscontrol.contract.SettingContract;
import com.vdin.accesscontrol.model.SettingModel;

/**
 * Created by new1 on 2018/11/14.
 * 设置界面presenter层
 */

public class SettingPresenter extends NetBasePresenter<SettingContract.ISettingView, SettingModel> implements SettingContract.ISettingPresenter {
    @Override
    protected SettingModel createModel() {
        return new SettingModel(this);
    }

    @Override
    public void onSuccess() {
        if (getView() == null) {
            return;
        }
        getView().cancelLoading();
        getView().logoutIntent();
    }

    @Override
    public void onFailure(int code, String err) {

    }

    @Override
    public void logout() {
        if (getView() == null) {
            return;
        }
        getView().showLoading();
        model.logout();
    }
}
