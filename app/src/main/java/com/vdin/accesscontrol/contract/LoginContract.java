package com.vdin.accesscontrol.contract;

import com.vdin.accesscontrol.base.netbase.NetBaseContract;

public interface LoginContract {

    interface ILoginView extends NetBaseContract.IbaseView {
        void loginIntent();
    }

    interface ILoginModel {

        void login();

    }

    interface ILoginPresenter extends NetBaseContract.IbasePresenter {
        void login();
    }
}
