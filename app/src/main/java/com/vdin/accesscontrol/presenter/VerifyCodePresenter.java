package com.vdin.accesscontrol.presenter;

import com.vdin.accesscontrol.base.netbase.NetBasePresenter;
import com.vdin.accesscontrol.contract.VerifyCodeContract;
import com.vdin.accesscontrol.model.VerifyCodeModel;

/**
 * Created by new1 on 2018/11/8.
 * 验证码presenter层
 */

public class VerifyCodePresenter extends NetBasePresenter<VerifyCodeContract.IVerifyView, VerifyCodeModel> implements VerifyCodeContract.IVerifyPresenter {

    public boolean isSignUp() {
        return isSignUp;
    }

    private boolean isSignUp;//是否是注册逻辑

    public VerifyCodePresenter(boolean isSignUp) {
        this.isSignUp = isSignUp;
    }

    @Override
    protected VerifyCodeModel createModel() {
        return new VerifyCodeModel();
    }
}
