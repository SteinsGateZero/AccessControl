package com.vdin.accesscontrol.presenter;

import com.vdin.accesscontrol.base.netbase.NetBasePresenter;
import com.vdin.accesscontrol.contract.FrontDeviceContract;
import com.vdin.accesscontrol.model.FrontDeviceModel;

/**
 * Created by new1 on 2018/11/12.
 * 前端设备列表presenter层
 */

public class FrontDevicePresenter extends NetBasePresenter<FrontDeviceContract.IDeviceView, FrontDeviceModel> implements FrontDeviceContract.IDevicePresenter {


    @Override
    protected FrontDeviceModel createModel() {
        return new FrontDeviceModel();
    }
}
