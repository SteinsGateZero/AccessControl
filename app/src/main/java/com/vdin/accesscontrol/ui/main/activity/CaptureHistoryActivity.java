package com.vdin.accesscontrol.ui.main.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vdin.accesscontrol.R;
import com.vdin.accesscontrol.adapter.DoorRecordAdapter;
import com.vdin.accesscontrol.base.netbase.NetBaseTitleActivity;
import com.vdin.accesscontrol.contract.CaptureHistoryContract;
import com.vdin.accesscontrol.model.CaptureHistoryModel;
import com.vdin.accesscontrol.model.bean.DoorRecordBean;
import com.vdin.accesscontrol.presenter.CaptureHistoryPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by new1 on 2018/11/19.
 * 抓拍记录界面
 */

public class CaptureHistoryActivity extends NetBaseTitleActivity<CaptureHistoryModel, CaptureHistoryContract.ICaptureView, CaptureHistoryPresenter> implements CaptureHistoryContract.ICaptureView, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.capture_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.capture_refresh)
    SwipeRefreshLayout refresh;
    int lastVisibleItem;
    List<DoorRecordBean> list;
    DoorRecordAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capturehistory);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        setBackArrow("抓拍记录");
        setRightImg(getResources().getDrawable(R.mipmap.icon_screening), this);
        list = new ArrayList<>();
        adapter = new DoorRecordAdapter(list, this);
        recyclerView.setAdapter(adapter);
        refresh.setColorSchemeResources(android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        refresh.setOnRefreshListener(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //判断RecyclerView的状态是空闲时,同时是最后一个可见的ITEM时才加载
                if (!refresh.isRefreshing() && newState == RecyclerView.SCROLL_STATE_IDLE && list.size() != 0 && lastVisibleItem + 1 == adapter.getItemCount()) {
                    // TODO: 2018/11/19 抓拍记录列表加载更多
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
    protected CaptureHistoryPresenter createPresenter() {
        return new CaptureHistoryPresenter();
    }

    @Override
    public void onClick(View v) {

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
                for (int i = 0; i < 11; i++) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    DoorRecordBean bean = new DoorRecordBean();
                    bean.setName("被抓拍人员" + i);
                    bean.setTime("2018-11-0" + i + " 13:00:00");
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
        // TODO: 2018/11/19 抓拍记录刷新操作
    }
}
