package com.vdin.accesscontrol.presenter;

import com.vdin.accesscontrol.base.netbase.NetBasePresenter;
import com.vdin.accesscontrol.contract.CreateMemberContract;
import com.vdin.accesscontrol.model.CreateMemberModel;

/**
 * Created by new1 on 2018/11/21.
 * 新增人员presenter层
 */

public class CreateMemberPresenter extends NetBasePresenter<CreateMemberContract.ICreateView, CreateMemberModel> implements CreateMemberContract.ICreatePresenter {
    @Override
    protected CreateMemberModel createModel() {
        return new CreateMemberModel();
    }
}
