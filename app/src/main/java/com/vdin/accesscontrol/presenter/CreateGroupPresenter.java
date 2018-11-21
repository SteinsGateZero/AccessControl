package com.vdin.accesscontrol.presenter;

import com.vdin.accesscontrol.base.netbase.NetBasePresenter;
import com.vdin.accesscontrol.contract.CreateGroupContract;
import com.vdin.accesscontrol.model.CreateGroupModel;

/**
 * Created by new1 on 2018/11/21.
 * 创建分组presenter层
 */

public class CreateGroupPresenter extends NetBasePresenter<CreateGroupContract.ICreateView, CreateGroupModel> implements CreateGroupContract.ICreatePresenter {

    @Override
    protected CreateGroupModel createModel() {
        return new CreateGroupModel();
    }
}
