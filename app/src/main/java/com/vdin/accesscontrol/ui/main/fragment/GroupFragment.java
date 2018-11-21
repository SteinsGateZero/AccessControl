package com.vdin.accesscontrol.ui.main.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.vdin.accesscontrol.R;
import com.vdin.accesscontrol.adapter.GroupAdapter;
import com.vdin.accesscontrol.adapter.GroupMemberAdapter;
import com.vdin.accesscontrol.base.netbase.NetBaseFragment;
import com.vdin.accesscontrol.contract.GroupFragmentContract;
import com.vdin.accesscontrol.model.GroupFragmentModel;
import com.vdin.accesscontrol.model.bean.GroupBean;
import com.vdin.accesscontrol.model.bean.MemberBean;
import com.vdin.accesscontrol.presenter.GroupFragmentPresenter;
import com.vdin.accesscontrol.presenter.MainPresenter;
import com.vdin.accesscontrol.ui.main.activity.CreateGroupActivity;
import com.vdin.accesscontrol.ui.main.activity.CreateMemberActivity;
import com.vdin.accesscontrol.utils.GroupPopWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by new1 on 2018/10/29.
 * 分组fragment
 */

public class GroupFragment extends NetBaseFragment<GroupFragmentModel, GroupFragmentContract.IGroupFragmentView, GroupFragmentPresenter> implements GroupFragmentContract.IGroupFragmentView, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, GroupMemberAdapter.GroupMemberListener {

    Dialog dialog;
    MainPresenter mainPresenter;
    GroupPopWindow popWindow;
    int lastVisibleItem;
    @BindView(R.id.frag_group_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.frag_group_refresh)
    SwipeRefreshLayout refresh;
    Unbinder unbinder;
    GroupAdapter adapter;
    List<GroupBean> list;

    public final GroupFragment setMainPresenter(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        list = new ArrayList<>();
        adapter = new GroupAdapter(list, this, this);
        popWindow = new GroupPopWindow(getContext(), this);
        refresh.setColorSchemeResources(android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        refresh.setOnRefreshListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //判断RecyclerView的状态是空闲时,同时是最后一个可见的ITEM时才加载
                if (!refresh.isRefreshing() && newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {
                    // TODO: 2018/11/20 分组列表加载

                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager == null) {
                    return;
                }
                //最后一个可见的ITEM
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }
        });
    }

    private void initTitle() {
        if (mainPresenter != null) {
            mainPresenter.getMainView().setTitle1(getString(R.string.main_title_group)).setTitle2(null).setTitle1Color(R.color.main_title_color).setTitle1Listener(null).setTitleRightImg(R.mipmap.icon_fz_add2, this).setTitle1Img(0).setTitle2Img(0);
        }
    }

    @Override
    protected GroupFragmentPresenter createPresenter() {
        return new GroupFragmentPresenter();
    }

    @Override
    public void selected() {
        initTitle();
        if (!refresh.isRefreshing() && list.size() == 0) {
            onRefresh();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mainPresenter.getMainView().setTitle2Listener(null).setTitle1Listener(null).setTitleRightImg(0, null);
        unbinder.unbind();
    }

    /**
     * 对分组人员进行的编辑弹窗操作
     */
    private void showDialog(int group, int member) {
        if (getContext() == null) {
            return;
        }
        dialog = new Dialog(getContext(), R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_group, null, false);
        //初始化控件
        Button name = inflate.findViewById(R.id.dialog_group_name);
        name.setText(list.get(group).getList().get(member).getName());
        Button move = inflate.findViewById(R.id.dialog_group_move);
        Button delete = inflate.findViewById(R.id.dialog_group_delete);
        Button cancel = inflate.findViewById(R.id.dialog_group_cancel);
        move.setOnClickListener(this);
        delete.setOnClickListener(this);
        cancel.setOnClickListener(this);
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        if (dialogWindow == null) {
            return;
        }
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 20;//设置Dialog距离底部的距离
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        //将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_group_move:
                dialog.dismiss();
                break;
            case R.id.dialog_group_delete:
                dialog.dismiss();
                break;
            case R.id.dialog_group_cancel:
                dialog.dismiss();
                break;
            case R.id.pop_btn_create_group:
                startActivity(new Intent(getContext(), CreateGroupActivity.class));
                popWindow.dismiss();
                break;
            case R.id.pop_btn_create_person:
                startActivity(new Intent(getContext(), CreateMemberActivity.class));
                popWindow.dismiss();
                break;
            case R.id.pop_btn_edit_group:
                popWindow.dismiss();
                break;
            case R.id.main_title_img:
                popWindow.showAtBottom(v);
                break;

        }
    }

    @Override
    public void onRefresh() {
        if (!refresh.isRefreshing()) {
            refresh.setRefreshing(true);
        }
        adapter.setRefresh(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                list.clear();
                for (int i = 0; i < 11; i++) {
                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    List<MemberBean> mList = new ArrayList<>();
                    GroupBean bean = new GroupBean();
                    for (int j = 0; j < 7; j++) {
                        MemberBean memberBean = new MemberBean();
                        memberBean.setName("张" + i + "国" + j);
                        mList.add(memberBean);
                    }
                    bean.setName("分组" + i);
                    bean.setNum(i);
                    bean.setPercent("30%");
                    bean.setList(mList);
                    list.add(bean);

                }
                if (getActivity() == null) {
                    return;
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setRefresh(false);
                        adapter.notifyDataSetChanged();
                        refresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    /**
     * @param group  所点击成员所在的group索引
     * @param member 所点击成员的索引
     *               分组人员点击回调
     */
    @Override
    public void onClick(int group, int member) {
        showDialog(group, member);
    }
}
