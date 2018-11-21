package com.vdin.accesscontrol.base.netbase;

public interface NetBaseContract {

    interface IbaseView {
        void showLoading();

        void cancelLoading();

        void showToast(String message);
    }

    interface IbasePresenter {

        void onSuccess();

        void onFailure(int code, String err);

    }
}
