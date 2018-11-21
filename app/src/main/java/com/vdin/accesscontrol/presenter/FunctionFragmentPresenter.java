package com.vdin.accesscontrol.presenter;

import com.vdin.accesscontrol.base.netbase.NetBasePresenter;
import com.vdin.accesscontrol.contract.FunctionFragmentContract;
import com.vdin.accesscontrol.model.FunctionFragmentModel;

/**
 * Created by new1 on 2018/10/29.
 */

public class FunctionFragmentPresenter extends NetBasePresenter<FunctionFragmentContract.IFunctionFragmentView, FunctionFragmentModel> implements FunctionFragmentContract.IFunctionFragmentPresenter {
    @Override
    protected FunctionFragmentModel createModel() {
        return new FunctionFragmentModel();
    }

    @Override
    public void refreshList() {

    }

    @Override
    public void loadMore() {

    }

    @Override
    public void changeListType() {

    }
}
