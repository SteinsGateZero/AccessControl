package com.vdin.accesscontrol.ui.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vdin.accesscontrol.R;
import com.vdin.accesscontrol.adapter.BoxAdapter;
import com.vdin.accesscontrol.base.netbase.NetBaseFragment;
import com.vdin.accesscontrol.contract.BoxFragmentContract;
import com.vdin.accesscontrol.model.BoxFragmentModel;
import com.vdin.accesscontrol.model.bean.BoxBean;
import com.vdin.accesscontrol.presenter.BoxFragmentPresenter;
import com.vdin.accesscontrol.presenter.MainPresenter;
import com.vdin.accesscontrol.ui.main.activity.DeviceListActivity;
import com.vdin.accesscontrol.ui.main.activity.DoorListActivity;
import com.vdin.accesscontrol.ui.main.activity.FrontDeviceActivity;
import com.vdin.accesscontrol.ui.main.activity.LocalNetActivity;
import com.vdin.accesscontrol.ui.main.activity.ThemeSetActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by new1 on 2018/10/29.
 * 首页盒子fragment
 */

public class BoxFragment extends NetBaseFragment<BoxFragmentModel, BoxFragmentContract.IBoxFragmentView, BoxFragmentPresenter> implements BoxFragmentContract.IBoxFragmentView, SwipeRefreshLayout.OnRefreshListener, BoxAdapter.BoxListener {

    MainPresenter mainPresenter;
    @BindView(R.id.frag_box_tv_online_num)
    TextView onlineNum;
    @BindView(R.id.frag_box_tv_offline_num)
    TextView offlineNum;
    @BindView(R.id.frag_box_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.frag_box_refresh)
    SwipeRefreshLayout refresh;
    Unbinder unbinder;
    int lastVisibleItem;
    BoxAdapter adapter;
    List<BoxBean> list;

    public final BoxFragment setMainPresenter(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_box, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        list = new ArrayList<>();
        refresh.setColorSchemeResources(android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        refresh.setOnRefreshListener(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //判断RecyclerView的状态是空闲时,同时是最后一个可见的ITEM时才加载
                //  if (!refresh.isRefreshing() && newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {
                // TODO: 2018/11/1 盒子列表加载
                // }
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
        adapter = new BoxAdapter(list, this, this);
        recyclerView.setAdapter(adapter);
    }

    private void initTitle() {
        if (mainPresenter != null) {
            mainPresenter.getMainView().setTitle1(getString(R.string.main_title_box)).setTitle2(null).setTitle1Color(R.color.main_title_color).setTitle1Listener(null).setTitleRightImg(0, null).setTitle1Img(0).setTitle2Img(0);
        }
    }

    @Override
    protected BoxFragmentPresenter createPresenter() {
        return new BoxFragmentPresenter();
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
        mainPresenter = null;
        unbinder.unbind();
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
                for (int i = 0; i < 7; i++) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    BoxBean boxBean = new BoxBean();
                    boxBean.setSpread(false);
                    boxBean.setName("盒子" + i);
                    boxBean.setDevice(10 + i);
                    boxBean.setDoor(1 + i);
                    boxBean.setScreen(2 + i);
                    list.add(boxBean);
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

    @Override
    public void doorDevice(int position) {
        startActivity(new Intent(getContext(), DoorListActivity.class));
        setAnim();
    }

    @Override
    public void device(int position) {
        startActivity(new Intent(getContext(), FrontDeviceActivity.class));
        setAnim();
    }

    @Override
    public void screenDevice(int position) {
        startActivity(new Intent(getContext(), DeviceListActivity.class));
        setAnim();
    }

    @Override
    public void localNet(int position) {
        startActivity(new Intent(getContext(), LocalNetActivity.class));
    }

    @Override
    public void backUp(int position) {

    }

    @Override
    public void boxTheme(int position) {
        startActivity(new Intent(getContext(), ThemeSetActivity.class));
    }

    private void setAnim() {
        if (getActivity() == null) {
            return;
        }
        getActivity().overridePendingTransition(R.anim.anim_activity_in, R.anim.anim_activity_stay);
    }
}
