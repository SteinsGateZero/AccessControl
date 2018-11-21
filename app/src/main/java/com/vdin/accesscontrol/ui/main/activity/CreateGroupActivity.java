package com.vdin.accesscontrol.ui.main.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.vdin.accesscontrol.R;
import com.vdin.accesscontrol.base.netbase.NetBaseTitleActivity;
import com.vdin.accesscontrol.contract.CreateGroupContract;
import com.vdin.accesscontrol.model.CreateGroupModel;
import com.vdin.accesscontrol.presenter.CreateGroupPresenter;

/**
 * Created by new1 on 2018/11/21.
 * 创建分组
 */

public class CreateGroupActivity extends NetBaseTitleActivity<CreateGroupModel, CreateGroupContract.ICreateView, CreateGroupPresenter> implements CreateGroupContract.ICreateView, View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creategroup);
        init();
    }

    private void init() {
        setBackArrow("创建分组");
        setRightText("确定", this);
    }

    @Override
    protected CreateGroupPresenter createPresenter() {
        return new CreateGroupPresenter();
    }

    @Override
    public void onClick(View v) {

    }
}
