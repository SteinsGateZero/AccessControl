package com.vdin.accesscontrol.ui.main.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.vdin.accesscontrol.R;
import com.vdin.accesscontrol.base.netbase.NetBaseTitleActivity;
import com.vdin.accesscontrol.contract.ChangePasswordContract;
import com.vdin.accesscontrol.model.ChangePassModel;
import com.vdin.accesscontrol.presenter.ChangePassPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by new1 on 2018/11/14.
 * 修改密码
 */

public class ChangePassActivity extends NetBaseTitleActivity<ChangePassModel, ChangePasswordContract.IPassView, ChangePassPresenter> implements ChangePasswordContract.IPassView, View.OnClickListener {

    @BindView(R.id.changePass_set_originPass)
    EditText originPass;
    @BindView(R.id.changePass_set_newPass)
    EditText newPass;
    @BindView(R.id.changePass_set_reNewPass)
    EditText reNewPass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepass);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        setBackArrow("修改密码");
        setRightText("保存", this);
    }

    @Override
    protected ChangePassPresenter createPresenter() {
        return new ChangePassPresenter();
    }

    @Override
    public void onClick(View v) {

    }
}
