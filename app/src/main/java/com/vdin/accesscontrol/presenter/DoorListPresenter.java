package com.vdin.accesscontrol.presenter;

import com.vdin.accesscontrol.base.netbase.NetBasePresenter;
import com.vdin.accesscontrol.contract.DoorListContract;
import com.vdin.accesscontrol.model.DoorListModel;

/**
 * Created by new1 on 2018/11/1.
 * 门禁列表presenter层
 */

public class DoorListPresenter extends NetBasePresenter<DoorListContract.IDoorListView, DoorListModel> implements DoorListContract.IDoorListPresenter {


    @Override
    protected DoorListModel createModel() {
        return new DoorListModel();
    }
}
