package com.vdin.accesscontrol.contract;

import com.vdin.accesscontrol.base.netbase.NetBaseContract;

public interface SettingContract {

    interface ISettingView extends NetBaseContract.IbaseView{
        void logoutIntent();
    }

    interface ISettingModel {

        void logout();

    }

    interface ISettingPresenter extends NetBaseContract.IbasePresenter{
        void logout();
    }
}
