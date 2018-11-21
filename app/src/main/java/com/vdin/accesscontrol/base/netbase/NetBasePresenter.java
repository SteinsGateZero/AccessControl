package com.vdin.accesscontrol.base.netbase;

import com.vdin.accesscontrol.base.BasePresenter;

public abstract class NetBasePresenter<V, M> extends BasePresenter<V> {
    protected M model;

    protected abstract M createModel();

    @Override
    public void onAttach(V view) {
        super.onAttach(view);
        model = createModel();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        model = null;
    }
}
