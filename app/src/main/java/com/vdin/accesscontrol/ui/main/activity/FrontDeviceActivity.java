package com.vdin.accesscontrol.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.baoyz.actionsheet.ActionSheet;
import com.vdin.accesscontrol.R;
import com.vdin.accesscontrol.adapter.FrontDeviceListAdapter;
import com.vdin.accesscontrol.base.netbase.NetBaseActivity;
import com.vdin.accesscontrol.contract.FrontDeviceContract;
import com.vdin.accesscontrol.model.FrontDeviceModel;
import com.vdin.accesscontrol.model.bean.FrontDeviceBean;
import com.vdin.accesscontrol.presenter.FrontDevicePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by new1 on 2018/11/12.
 * 前端设备列表界面
 */

public class FrontDeviceActivity extends NetBaseActivity<FrontDeviceModel, FrontDeviceContract.IDeviceView, FrontDevicePresenter> implements FrontDeviceContract.IDeviceView, SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener, ActionSheet.ActionSheetListener {


    @BindView(R.id.door_list_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.door_list_refresh)
    SwipeRefreshLayout refresh;
    List<FrontDeviceBean> list;
    FrontDeviceListAdapter adapter;
    int lastVisibleItem;
    @BindView(R.id.door_list_title)
    TextView title;
    GridLayoutManager manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door_list);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        title.setText(getText(R.string.frontDevice_list));
        list = new ArrayList<>();
        adapter = new FrontDeviceListAdapter(list, this, this);
        manager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        refresh.setColorSchemeResources(android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        refresh.setOnRefreshListener(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //判断RecyclerView的状态是空闲时,同时是最后一个可见的ITEM时才加载
                if (!refresh.isRefreshing() && newState == RecyclerView.SCROLL_STATE_IDLE && list.size() != 0 && lastVisibleItem + 1 == adapter.getItemCount()) {
                    // TODO: 2018/11/5 前端设备列表加载
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
    public void onRefresh() {
        if (!refresh.isRefreshing()) {
            refresh.setRefreshing(true);
        }

        adapter.setRefresh(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 8; i++) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    FrontDeviceBean bean = new FrontDeviceBean();
                    bean.setName("摄像头" + i);
                    if (i % 2 != 0) {
                        bean.setOnline(true);
                        bean.setImg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1542010561044&di=3f373f133b5270a4e3858a7cc89cf769&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0181845834f4eda8012060c8c95113.JPG%401280w_1l_2o_100sh.png");
                    } else {
                        bean.setOffline("离线时间:2018:0" + i + ".02 " + "13:0" + i);
                    }
                    list.add(bean);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setRefresh(false);
                        //因为是grid显示，如果视图列表为空，则将横向最大item置为1
                        if (list.size() > 0) {
                            manager.setSpanCount(2);
                        } else {
                            manager.setSpanCount(1);
                        }
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
    protected FrontDevicePresenter createPresenter() {
        return new FrontDevicePresenter();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ActionSheet.createBuilder(this, getSupportFragmentManager())
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles("查看前端设备", "查看抓拍记录")
                .setCancelableOnTouchOutside(true)
                .setListener(this).setTag(String.valueOf(position)).show();
    }

    @Override
    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

    }

    @Override
    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
        if (index == 0) {
            Intent intent = new Intent(FrontDeviceActivity.this, FullScreenDeviceActivity.class);
            intent.putExtra("img", list.get(Integer.valueOf(actionSheet.getTag())).getImg());
            intent.putExtra("name", list.get(Integer.valueOf(actionSheet.getTag())).getName());
            startActivity(intent);
        } else {
            startActivity(new Intent(this, CaptureHistoryActivity.class));
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_activity_stay, R.anim.anim_activity_out);
    }
}
