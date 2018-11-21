package com.vdin.accesscontrol.ui.main.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.baoyz.actionsheet.ActionSheet;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vdin.accesscontrol.R;
import com.vdin.accesscontrol.base.netbase.NetBaseTitleActivity;
import com.vdin.accesscontrol.contract.ChangeDataContract;
import com.vdin.accesscontrol.model.ChangeDataModel;
import com.vdin.accesscontrol.presenter.ChangeDataPresenter;
import com.vdin.accesscontrol.utils.CameraUtils;
import com.vdin.accesscontrol.utils.Permission;
import com.vdin.accesscontrol.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

/**
 * Created by new1 on 2018/11/16.
 * 修改或完善密码界面
 */

public class ChangeDataActivity extends NetBaseTitleActivity<ChangeDataModel, ChangeDataContract.IDataView, ChangeDataPresenter> implements ChangeDataContract.IDataView, View.OnClickListener, ActionSheet.ActionSheetListener {

    @BindView(R.id.changeData_camera)
    ImageView takeCamera;
    ActionSheet actionSheet;
    @BindView(R.id.changeData_set_head)
    ImageView headImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changedata);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        setBackArrow("完善资料");
        setRightText("保存", this);
        takeCamera.setOnClickListener(this);
    }

    @Override
    protected ChangeDataPresenter createPresenter() {
        return new ChangeDataPresenter();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.changeData_camera:
                if (actionSheet == null) {
                    actionSheet = ActionSheet.createBuilder(this, getSupportFragmentManager())
                            .setCancelButtonTitle("取消")
                            .setOtherButtonTitles("拍照", "相册")
                            .setCancelableOnTouchOutside(true)
                            .setListener(this).show();
                } else {
                    actionSheet.show(getSupportFragmentManager(), null);
                }
                break;
            case R.id.titleBar_tv_right:
                break;
        }
    }

    @Override
    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

    }

    @Override
    public void onOtherButtonClick(ActionSheet actionSheet, final int index) {
        //请求拍照与存储权限
        Permission.getInstance().requestPermission(this, new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    if (index == 1) {
                        CameraUtils.getByAlbum(ChangeDataActivity.this);
                    } else {
                        CameraUtils.getByCamera(ChangeDataActivity.this);
                    }
                } else {
                    ToastUtils.showToast("没有相应权限");
                }
            }
        }, Permission.CAMERA, Permission.STORAGE);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = CameraUtils.onActivityResult(this, requestCode, resultCode, data);
        if (null != uri) { // 当不为空时获取到图片Uri
            Glide.with(this).load(CameraUtils.getImageAbsolutePath(this, uri)).apply(new RequestOptions().circleCrop()).into(headImg);
        }
    }

}
