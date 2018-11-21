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
import com.vdin.accesscontrol.contract.VerifyCodeContract;
import com.vdin.accesscontrol.eventbus.CountDownEvent;
import com.vdin.accesscontrol.model.VerifyCodeModel;
import com.vdin.accesscontrol.presenter.VerifyCodePresenter;
import com.vdin.accesscontrol.utils.CountDownUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.vdin.accesscontrol.ui.loginAndSplash.SignUpOrForgetActivity.INTENT_TYPE;

/**
 * Created by new1 on 2018/11/8.
 * 验证码界面
 */

public class VerifyCodeActivity extends NetBaseTitleActivity<VerifyCodeModel, VerifyCodeContract.IVerifyView, VerifyCodePresenter> implements VerifyCodeContract.IVerifyView {

    @BindView(R.id.verify_edt)
    EditText edt;
    @BindView(R.id.verify_edt_line)
    View edtLine;
    @BindView(R.id.verify_time)
    TextView verifyTime;
    @BindView(R.id.verify_resend)
    TextView reSend;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificationcode);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        EventBus.getDefault().register(this);
        setBackArrow("返回");
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
        CountDownUtils.start(mPresenter.isSignUp());
    }

    @Override
    protected VerifyCodePresenter createPresenter() {
        return new VerifyCodePresenter(getIntent().getBooleanExtra(INTENT_TYPE, false));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getCount(CountDownEvent event) {
        if (event.getCount() == 0) {
            verifyTime.setText("");
            reSend.setVisibility(View.VISIBLE);
            return;
        }
        if (event.isSignUp() == mPresenter.isSignUp()) {
            StringBuilder stringBuilder;
            stringBuilder = new StringBuilder("已发送(");
            stringBuilder.append(event.getCount()).append(")");
            verifyTime.setText(stringBuilder.toString());
        }

    }

    @OnClick({R.id.verify_resend, R.id.verify_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.verify_resend:
                reSend.setVisibility(View.GONE);
                CountDownUtils.start(mPresenter.isSignUp());
                break;
            case R.id.verify_next:
                Intent intent = new Intent(VerifyCodeActivity.this, PasswordsActivity.class);
                intent.putExtra(INTENT_TYPE, mPresenter.isSignUp());
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
