package com.vdin.accesscontrol.base.netbase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public abstract class NetBaseFragment<M, V, P extends NetBasePresenter<V, M>> extends Fragment {

    protected P mPresenter;

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.onAttach((V) this);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
        mPresenter = null;
    }

    protected abstract P createPresenter();
}
