package com.vdin.accesscontrol.contract;

public interface SignUpOrForgetContract {

    interface ISignForgetView {
        void setViewString(String mainTitle, String tips);
    }

    interface ISignForgetModel {

    }

    interface ISignForgetPresenter {
        void inject();
    }
}
