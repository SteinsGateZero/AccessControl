package com.vdin.accesscontrol.presenter;

import com.vdin.accesscontrol.base.netbase.NetBasePresenter;
import com.vdin.accesscontrol.contract.ChangePasswordContract;
import com.vdin.accesscontrol.model.ChangePassModel;

/**
 * Created by new1 on 2018/11/14.
 * 修改密码presenter层
 */

public class ChangePassPresenter extends NetBasePresenter<ChangePasswordContract.IPassView, ChangePassModel> implements ChangePasswordContract.IPassPresenter {
    @Override
    protected ChangePassModel createModel() {
        return new ChangePassModel();
    }
}
