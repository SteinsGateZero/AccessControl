package com.vdin.accesscontrol.contract;

public interface PasswordsContract {

    interface IPassView {
        void setViewString(String mainTitle);
    }

    interface IPassModel {

    }

    interface IPassPresenter {
        void inject();
    }
}
