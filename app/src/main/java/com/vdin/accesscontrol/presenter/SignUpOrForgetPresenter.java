package com.vdin.accesscontrol.presenter;

import com.vdin.accesscontrol.base.netbase.NetBasePresenter;
import com.vdin.accesscontrol.contract.SignUpOrForgetContract;
import com.vdin.accesscontrol.model.SignUpOrForgetModel;

/**
 * Created by new1 on 2018/11/8.
 * 注册与忘记密码手机号码输入presenter层
 */

public class SignUpOrForgetPresenter extends NetBasePresenter<SignUpOrForgetContract.ISignForgetView, SignUpOrForgetModel> implements SignUpOrForgetContract.ISignForgetPresenter {

    public boolean isSignUp() {
        return isSignUp;
    }

    private boolean isSignUp;

    public SignUpOrForgetPresenter(boolean isSignUp) {
        this.isSignUp = isSignUp;
    }

    @Override
    protected SignUpOrForgetModel createModel() {
        return new SignUpOrForgetModel();
    }

    @Override
    public void inject() {
        if(getView() == null){
            return;
        }
        if (isSignUp) {
            getView().setViewString("欢迎注册", "请告诉我们，您的手机号");
            return;
        }
        getView().setViewString("忘记密码", "请填写您注册时的手机号");
    }
}
