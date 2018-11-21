package com.vdin.accesscontrol.presenter;

import com.vdin.accesscontrol.base.netbase.NetBasePresenter;
import com.vdin.accesscontrol.contract.PasswordsContract;
import com.vdin.accesscontrol.model.PasswordsModel;

/**
 * Created by new1 on 2018/11/9.
 * 设置密码与忘记重置密码presenter层
 */

public class PasswordsPresenter extends NetBasePresenter<PasswordsContract.IPassView, PasswordsModel> implements PasswordsContract.IPassPresenter {

    public boolean isSignUp() {
        return isSignUp;
    }

    private boolean isSignUp;

    public PasswordsPresenter(boolean isSignUp) {
        this.isSignUp = isSignUp;
    }

    @Override
    protected PasswordsModel createModel() {
        return new PasswordsModel();
    }

    @Override
    public void inject() {
        if (getView() == null) {
            return;
        }
        if (isSignUp) {
            getView().setViewString("设置密码");
        } else {
            getView().setViewString("请输入新密码");
        }
    }
}
