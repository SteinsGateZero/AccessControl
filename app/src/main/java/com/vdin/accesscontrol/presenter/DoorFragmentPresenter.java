package com.vdin.accesscontrol.presenter;

import com.vdin.accesscontrol.base.netbase.NetBasePresenter;
import com.vdin.accesscontrol.contract.DoorFragmentContract;
import com.vdin.accesscontrol.model.DoorFragmentModel;

/**
 * Created by new1 on 2018/10/31.
 * 首页功能模块开门记录presenter层
 */

public class DoorFragmentPresenter extends NetBasePresenter<DoorFragmentContract.IDoorFragmentView, DoorFragmentModel> implements DoorFragmentContract.IDoorFragmentPresenter {
    @Override
    protected DoorFragmentModel createModel() {
        return new DoorFragmentModel();
    }

    @Override
    public void refreshList() {

    }

    @Override
    public void loadMore() {

    }

}
