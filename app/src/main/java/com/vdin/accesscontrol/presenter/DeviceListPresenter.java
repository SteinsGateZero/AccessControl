package com.vdin.accesscontrol.presenter;

import com.vdin.accesscontrol.base.netbase.NetBasePresenter;
import com.vdin.accesscontrol.contract.DeviceListContract;
import com.vdin.accesscontrol.model.DeviceListModel;

/**
 * Created by new1 on 2018/11/12.
 * 显示设备列表presenter层
 */

public class DeviceListPresenter extends NetBasePresenter<DeviceListContract.IDeviceListView, DeviceListModel> implements DeviceListContract.IDeviceListPresenter {


    @Override
    protected DeviceListModel createModel() {
        return new DeviceListModel();
    }
}
