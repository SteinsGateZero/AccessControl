package com.vdin.accesscontrol.ui.loginAndSplash;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.vdin.accesscontrol.R;
import com.vdin.accesscontrol.base.netbase.NetBaseTitleActivity;
import com.vdin.accesscontrol.contract.SignUpOrForgetContract;
import com.vdin.accesscontrol.model.SignUpOrForgetModel;
import com.vdin.accesscontrol.presenter.SignUpOrForgetPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by new1 on 2018/11/8.
 * 注册或者忘记密码填写手机号码界面
 */

public class SignUpOrForgetActivity extends NetBaseTitleActivity<SignUpOrForgetModel, SignUpOrForgetContract.ISignForgetView, SignUpOrForgetPresenter> implements SignUpOrForgetContract.ISignForgetView {

    public static final String INTENT_TYPE = "IsSignUp";
    @BindView(R.id.signUp_mainTitle)
    TextView mainTitle;
    @BindView(R.id.signUp_tips)
    TextView tips;
    @BindView(R.id.signUp_edt)
    EditText edt;
    @BindView(R.id.signUp_edt_line)
    View edtLine;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signuporforget);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        setBackArrow("返回");
        mPresenter.inject();//界面复用view的显示描述
        edt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    edtLine.setBackgroundColor(Color.parseColor("#398EEB"));
                } else {
                    edtLine.setBackgroundColor(Color.parseColor("#E3E1E7"));
                }
            }
        });
    }

    @Override
    protected SignUpOrForgetPresenter createPresenter() {
        return new SignUpOrForgetPresenter(getIntent().getBooleanExtra(INTENT_TYPE, false));
    }

    @Override
    public void setViewString(String mainTitle, String tips) {
        this.mainTitle.setText(mainTitle);
        this.tips.setText(tips);
    }

    @OnClick(R.id.signUp_next)
    public void onViewClicked() {
        Intent intent = new Intent(this, VerifyCodeActivity.class);
        intent.putExtra(INTENT_TYPE, mPresenter.isSignUp());
        startActivity(intent);
    }
}
