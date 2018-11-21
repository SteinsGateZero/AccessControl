package com.vdin.accesscontrol.model;

import com.vdin.accesscontrol.contract.LoginContract;
import com.vdin.accesscontrol.presenter.LoginPresenter;
import com.vdin.accesscontrol.utils.LoginUtils;

/**
 * Created by new1 on 2018/11/1.
 * 登录逻辑model层
 */

public class LoginModel implements LoginContract.ILoginModel {

    private LoginPresenter presenter;

    public LoginModel(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void login() {
        LoginUtils.getInstance().login();
        presenter.onSuccess();
    }
}
