package com.vdin.accesscontrol.presenter;

import com.vdin.accesscontrol.base.netbase.NetBasePresenter;
import com.vdin.accesscontrol.contract.CaptureHistoryContract;
import com.vdin.accesscontrol.model.CaptureHistoryModel;

/**
 * Created by new1 on 2018/11/19.
 * 抓拍记录presenter层
 */

public class CaptureHistoryPresenter extends NetBasePresenter<CaptureHistoryContract.ICaptureView, CaptureHistoryModel> implements CaptureHistoryContract.ICapturePresenter {
    @Override
    protected CaptureHistoryModel createModel() {
        return new CaptureHistoryModel();
    }
}
