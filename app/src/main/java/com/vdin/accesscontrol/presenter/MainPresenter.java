package com.vdin.accesscontrol.presenter;

import com.vdin.accesscontrol.base.netbase.NetBasePresenter;
import com.vdin.accesscontrol.contract.MainContract;
import com.vdin.accesscontrol.model.MainModel;

/**
 * Created by new1 on 2018/10/29.
 * 主界面presenter层
 */

public class MainPresenter extends NetBasePresenter<MainContract.IMainView, MainModel> implements MainContract.IMainPresenter {
    @Override
    protected MainModel createModel() {
        return new MainModel();
    }

    @Override
    public MainContract.IMainView getMainView() {
        return getView();
    }

    @Override
    public void doDestroy() {

    }
}
