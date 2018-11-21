package com.vdin.accesscontrol.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public abstract class BasePresenter<V> implements BaseContract.IbasePresenter<V> {

    private Reference<V> mReference = null;

    @Override
    public void onAttach(V view) {
        mReference = new WeakReference<V>(view);
    }

    @Override
    public void onDetach() {
        if (mReference != null) {
            mReference.clear();
            mReference = null;
        }
    }

    protected V getView() {
        return mReference.get();
    }
}
