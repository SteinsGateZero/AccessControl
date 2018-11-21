package com.vdin.accesscontrol.ui.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vdin.accesscontrol.R;
import com.vdin.accesscontrol.base.netbase.NetBaseFragment;
import com.vdin.accesscontrol.contract.MyFragmentContract;
import com.vdin.accesscontrol.model.MyFragmentModel;
import com.vdin.accesscontrol.presenter.MainPresenter;
import com.vdin.accesscontrol.presenter.MyFragmentPresenter;
import com.vdin.accesscontrol.ui.main.activity.ChangeDataActivity;
import com.vdin.accesscontrol.ui.main.activity.ChangePassActivity;
import com.vdin.accesscontrol.ui.main.activity.SettingActivity;
import com.vdin.accesscontrol.utils.ToastUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by new1 on 2018/10/29.
 * 我的fragment
 */

public class MyFragment extends NetBaseFragment<MyFragmentModel, MyFragmentContract.IMyFragmentView, MyFragmentPresenter> implements MyFragmentContract.IMyFragmentView {


    MainPresenter mainPresenter;
    Unbinder unbinder;

    public final MyFragment setMainPresenter(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void initTitle() {
        if (mainPresenter != null) {
            mainPresenter.getMainView().setTitle1(null).setTitle2(null).setTitle1Listener(null).setTitleRightImg(0, null).setTitle1Img(0).setTitle2Img(0);
        }
    }

    @Override
    protected MyFragmentPresenter createPresenter() {
        return new MyFragmentPresenter();
    }

    @Override
    public void selected() {
        initTitle();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mainPresenter = null;
        unbinder.unbind();
    }

    @OnClick({R.id.frag_my_change, R.id.frag_my_btn_address, R.id.frag_my_btn_pass, R.id.frag_my_btn_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.frag_my_change:
                startActivity(new Intent(getContext(), ChangeDataActivity.class));
                break;
            case R.id.frag_my_btn_address:
                ToastUtils.showToast("地址");
                break;
            case R.id.frag_my_btn_pass:
                startActivity(new Intent(getContext(), ChangePassActivity.class));
                break;
            case R.id.frag_my_btn_set:
                startActivity(new Intent(getContext(), SettingActivity.class));
                break;
        }
    }
}
