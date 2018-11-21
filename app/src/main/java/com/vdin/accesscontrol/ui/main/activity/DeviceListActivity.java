package com.vdin.accesscontrol.ui.main.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.vdin.accesscontrol.R;
import com.vdin.accesscontrol.adapter.DeviceListAdapter;
import com.vdin.accesscontrol.base.netbase.NetBaseActivity;
import com.vdin.accesscontrol.contract.DeviceListContract;
import com.vdin.accesscontrol.model.DeviceListModel;
import com.vdin.accesscontrol.model.bean.DeviceListBean;
import com.vdin.accesscontrol.presenter.DeviceListPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by new1 on 2018/11/5.
 * 门禁设备列表
 */

public class DeviceListActivity extends NetBaseActivity<DeviceListModel, DeviceListContract.IDeviceListView, DeviceListPresenter> implements DeviceListContract.IDeviceListView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.door_list_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.door_list_refresh)
    SwipeRefreshLayout refresh;
    List<DeviceListBean> list;
    DeviceListAdapter adapter;
    int lastVisibleItem;
    @BindView(R.id.door_list_title)
    TextView title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door_list);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        title.setText(getText(R.string.device_list));
        list = new ArrayList<>();
        adapter = new DeviceListAdapter(list, this);
        recyclerView.setAdapter(adapter);
        refresh.setColorSchemeResources(android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        refresh.setOnRefreshListener(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //判断RecyclerView的状态是空闲时,同时是最后一个可见的ITEM时才加载
                if (!refresh.isRefreshing() && newState == RecyclerView.SCROLL_STATE_IDLE && list.size() != 0 && lastVisibleItem + 1 == adapter.getItemCount()) {
                    // TODO: 2018/11/12 显示设备列表加载
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
        //首次刷新加载
        onRefresh();
    }


    @Override
    protected DeviceListPresenter createPresenter() {
        return new DeviceListPresenter();
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
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    DeviceListBean bean = new DeviceListBean();
                    bean.setDeviceName("显示" + i);
                    if (i % 2 == 0) {
                        bean.setOnline(true);
                    }
                    list.add(bean);
                }
                runOnUiThread(new Runnable() {
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

    @OnClick(R.id.door_list_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_activity_stay, R.anim.anim_activity_out);
    }
}
