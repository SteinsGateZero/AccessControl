package com.vdin.accesscontrol.ui.loginAndSplash;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.vdin.accesscontrol.R;
import com.vdin.accesscontrol.base.netbase.NetBaseActivity;
import com.vdin.accesscontrol.contract.LoginContract;
import com.vdin.accesscontrol.model.LoginModel;
import com.vdin.accesscontrol.presenter.LoginPresenter;
import com.vdin.accesscontrol.ui.main.activity.MainActivity;
import com.vdin.accesscontrol.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by new1 on 2018/11/1.
 * 登录界面
 */

public class LoginActivity extends NetBaseActivity<LoginModel, LoginContract.ILoginView, LoginPresenter> implements LoginContract.ILoginView {

    @BindView(R.id.login_edt_phone)
    TextInputEditText edtPhone;
    @BindView(R.id.login_input_phone)
    TextInputLayout inputPhone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        edtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s.toString())) {
                    inputPhone.setError("请输入手机号码");
                    edtPhone.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                    return;
                }
                Drawable rightDrawable = getResources().getDrawable(R.mipmap.icon_close);
                edtPhone.setCompoundDrawablesWithIntrinsicBounds(null, null, rightDrawable, null);
                if (s.length() < 7) {
                    inputPhone.setError("请输入正确的手机号");
                } else {
                    inputPhone.setError("");
                }
            }
        });

    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @OnClick({R.id.login_btn, R.id.login_forget, R.id.login_signUp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                mPresenter.login();
                break;
            case R.id.login_forget:
                Intent intent = new Intent();
                intent.setClass(this, SignUpOrForgetActivity.class);
                intent.putExtra(SignUpOrForgetActivity.INTENT_TYPE, false);
                startActivity(intent);
                break;
            case R.id.login_signUp:
                Intent intent1 = new Intent();
                intent1.setClass(this, SignUpOrForgetActivity.class);
                intent1.putExtra(SignUpOrForgetActivity.INTENT_TYPE, true);
                startActivity(intent1);
                break;
        }

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void cancelLoading() {

    }

    @Override
    public void showToast(String message) {
        ToastUtils.showToast(message);
    }

    @Override
    public void loginIntent() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }
}
