package com.vdin.accesscontrol.presenter;

import com.vdin.accesscontrol.base.netbase.NetBasePresenter;
import com.vdin.accesscontrol.contract.ChangeDataContract;
import com.vdin.accesscontrol.model.ChangeDataModel;

/**
 * Created by new1 on 2018/11/16.
 * 修改资料或完善资料presenter层
 */

public class ChangeDataPresenter extends NetBasePresenter<ChangeDataContract.IDataView, ChangeDataModel> implements ChangeDataContract.IDataPresenter {
    @Override
    protected ChangeDataModel createModel() {
        return new ChangeDataModel();
    }
}
