package com.vdin.accesscontrol.presenter;

import com.vdin.accesscontrol.base.netbase.NetBasePresenter;
import com.vdin.accesscontrol.contract.MyFragmentContract;
import com.vdin.accesscontrol.model.MyFragmentModel;

/**
 * Created by new1 on 2018/10/29.
 * 个人中心主界面
 */

public class MyFragmentPresenter extends NetBasePresenter<MyFragmentContract.IMyFragmentView, MyFragmentModel> implements MyFragmentContract.IMyFragmentPresenter {

    @Override
    protected MyFragmentModel createModel() {
        return new MyFragmentModel();
    }
}
