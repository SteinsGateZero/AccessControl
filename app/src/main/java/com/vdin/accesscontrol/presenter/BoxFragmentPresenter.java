package com.vdin.accesscontrol.presenter;

import com.vdin.accesscontrol.base.netbase.NetBasePresenter;
import com.vdin.accesscontrol.contract.BoxFragmentContract;
import com.vdin.accesscontrol.model.BoxFragmentModel;

/**
 * Created by new1 on 2018/10/29.
 * 盒子列表fragment presenter层
 */

public class BoxFragmentPresenter extends NetBasePresenter<BoxFragmentContract.IBoxFragmentView, BoxFragmentModel> implements BoxFragmentContract.IBoxFragmentPresenter {

    @Override
    protected BoxFragmentModel createModel() {
        return new BoxFragmentModel();
    }
}
