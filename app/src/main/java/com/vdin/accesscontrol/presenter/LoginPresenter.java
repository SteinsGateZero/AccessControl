package com.vdin.accesscontrol.presenter;

import com.vdin.accesscontrol.base.netbase.NetBasePresenter;
import com.vdin.accesscontrol.contract.LoginContract;
import com.vdin.accesscontrol.model.LoginModel;

/**
 * Created by new1 on 2018/11/1.
 * 登录presenter层
 */

public class LoginPresenter extends NetBasePresenter<LoginContract.ILoginView, LoginModel> implements LoginContract.ILoginPresenter {


    @Override
    protected LoginModel createModel() {
        return new LoginModel(this);
    }

    @Override
    public void onSuccess() {
        if(getView() == null){
            return;
        }
        getView().cancelLoading();
        getView().loginIntent();
    }

    @Override
    public void onFailure(int code, String err) {
        if(getView() == null){
            return;
        }
        getView().cancelLoading();
    }

    @Override
    public void login() {
        if(getView() == null){
            return;
        }
        getView().showLoading();
        model.login();
    }
}
