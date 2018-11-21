package com.vdin.accesscontrol.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseTitleActivity<V, P extends BasePresenter<V>> extends CommonTitleActivity {

    protected P mPresenter;

    @SuppressWarnings({"unchecked"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.onAttach((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
        mPresenter = null;
    }

    protected abstract P createPresenter();
}
