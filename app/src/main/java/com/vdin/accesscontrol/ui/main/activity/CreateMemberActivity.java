package com.vdin.accesscontrol.ui.main.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.readTwoGeneralCard.Serverinfo;
import com.steinsgatezero.vdinidcard.CardListenter;
import com.steinsgatezero.vdinidcard.IdCardBean;
import com.steinsgatezero.vdinidcard.VdinIdCard;
import com.vdin.accesscontrol.R;
import com.vdin.accesscontrol.base.netbase.NetBaseTitleActivity;
import com.vdin.accesscontrol.contract.CreateMemberContract;
import com.vdin.accesscontrol.model.CreateMemberModel;
import com.vdin.accesscontrol.presenter.CreateMemberPresenter;
import com.vdin.accesscontrol.utils.Permission;
import com.vdin.accesscontrol.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

/**
 * Created by new1 on 2018/11/21.
 * 新增人员
 */

public class CreateMemberActivity extends NetBaseTitleActivity<CreateMemberModel, CreateMemberContract.ICreateView, CreateMemberPresenter> implements CreateMemberContract.ICreateView, View.OnClickListener, CardListenter {
    @BindView(R.id.createM_set_name)
    EditText name;
    private VdinIdCard idCard;
    private boolean isFirst = true;//防止两次获取，第一次onCreate中也会初始化一次NFC适配器

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createmember);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (idCard != null && idCard.isNFC()) {
            if (!isFirst) {
                idCard.initNfcAdapter();
            }
            isFirst = false;
            idCard.registerNfc();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (idCard != null && idCard.isNFC()) {
            idCard.unRegisterNfc();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (idCard != null) {
            idCard.destroy();
            idCard = null;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        idCard.readCard(intent);
    }

    private void init() {
        setBackArrow("新增人员");
        setRightText("确定", this);
        //请求权限
        Permission.getInstance().requestPermission(this, new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    initNFC();
                } else {
                    ToastUtils.showToast("没有相应权限");
                }
            }
        }, Permission.PHONE[0]);
        showDialogTips();
    }

    private void showDialogTips() {
        Dialog dialog=new Dialog(this, R.style.dialog_nfc);
        View view= LayoutInflater.from(this).inflate(R.layout.dialog_nfc, null, false);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();
    }

    private void initNFC() {
        idCard = new VdinIdCard.Builder(CreateMemberActivity.this, "4ba7441a8f7181c8c2cfd9e3de12995b", new Serverinfo("id.vdin01.com", 80), this).isNFC(true).isTestServer(false).build();
    }

    @Override
    protected CreateMemberPresenter createPresenter() {
        return new CreateMemberPresenter();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onSucceed(IdCardBean idCardBean) {
        name.setText(idCardBean.getName());
    }

    @Override
    public void onFailed(int i) {

    }
}
