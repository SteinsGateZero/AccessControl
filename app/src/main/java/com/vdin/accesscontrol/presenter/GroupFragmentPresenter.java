package com.vdin.accesscontrol.presenter;

import com.vdin.accesscontrol.base.netbase.NetBasePresenter;
import com.vdin.accesscontrol.contract.GroupFragmentContract;
import com.vdin.accesscontrol.model.GroupFragmentModel;

/**
 * Created by new1 on 2018/10/29.
 */

public class GroupFragmentPresenter extends NetBasePresenter<GroupFragmentContract.IGroupFragmentView, GroupFragmentModel> implements GroupFragmentContract.IGroupFragmentPresenter {

    @Override
    protected GroupFragmentModel createModel() {
        return new GroupFragmentModel();
    }
}
