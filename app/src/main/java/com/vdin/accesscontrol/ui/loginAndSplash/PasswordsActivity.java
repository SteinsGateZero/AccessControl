package com.vdin.accesscontrol.ui.loginAndSplash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.vdin.accesscontrol.R;
import com.vdin.accesscontrol.base.netbase.NetBaseTitleActivity;
import com.vdin.accesscontrol.contract.PasswordsContract;
import com.vdin.accesscontrol.model.PasswordsModel;
import com.vdin.accesscontrol.presenter.PasswordsPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.vdin.accesscontrol.ui.loginAndSplash.SignUpOrForgetActivity.INTENT_TYPE;

/**
 * Created by new1 on 2018/11/9.
 * 设置密码与忘记重置密码界面
 */

public class PasswordsActivity extends NetBaseTitleActivity<PasswordsModel, PasswordsContract.IPassView, PasswordsPresenter> implements PasswordsContract.IPassView {

    @BindView(R.id.passwords_mainTitle)
    TextView mainTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwords);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        setBackArrow("返回");
        mPresenter.inject();//更新复用界面的不同描述
    }

    @Override
    protected PasswordsPresenter createPresenter() {
        return new PasswordsPresenter(getIntent().getBooleanExtra(INTENT_TYPE, false));
    }

    @Override
    public void setViewString(String mainTitle) {
        this.mainTitle.setText(mainTitle);
    }

    @OnClick(R.id.passwords_next)
    public void onViewClicked() {
        startActivity(new Intent(PasswordsActivity.this, LoginActivity.class));
    }
}
