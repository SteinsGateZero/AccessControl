package com.vdin.accesscontrol.presenter;

import com.vdin.accesscontrol.base.netbase.NetBasePresenter;
import com.vdin.accesscontrol.contract.AttendanceFragmentContract;
import com.vdin.accesscontrol.model.AttendanceFragmentModel;

/**
 * Created by new1 on 2018/10/29.
 * 考勤列表fragment presenter层
 */

public class AttendanceFragmentPresenter extends NetBasePresenter<AttendanceFragmentContract.IAttendanceFragmentView, AttendanceFragmentModel> implements AttendanceFragmentContract.IAttendanceFragmentPresenter {
    @Override
    protected AttendanceFragmentModel createModel() {
        return new AttendanceFragmentModel();
    }

    @Override
    public void refreshList() {

    }

    @Override
    public void loadMore() {

    }

}
