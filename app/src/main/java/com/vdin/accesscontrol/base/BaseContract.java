package com.vdin.accesscontrol.base;

public interface BaseContract {

    interface IbasePresenter<V> {

        void onAttach(V view);

        void onDetach();

    }
}
