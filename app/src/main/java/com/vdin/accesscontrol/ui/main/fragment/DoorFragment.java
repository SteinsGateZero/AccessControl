package com.vdin.accesscontrol.ui.main.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vdin.accesscontrol.R;
import com.vdin.accesscontrol.adapter.DoorRecordAdapter;
import com.vdin.accesscontrol.base.netbase.NetBaseFragment;
import com.vdin.accesscontrol.contract.DoorFragmentContract;
import com.vdin.accesscontrol.model.DoorFragmentModel;
import com.vdin.accesscontrol.model.bean.DoorRecordBean;
import com.vdin.accesscontrol.presenter.DoorFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by new1 on 2018/10/29.
 * 开门人员列表页面
 */

public class DoorFragment extends NetBaseFragment<DoorFragmentModel, DoorFragmentContract.IDoorFragmentView, DoorFragmentPresenter> implements DoorFragmentContract.IDoorFragmentView, SwipeRefreshLayout.OnRefreshListener {

    DoorRecordAdapter adapter;
    @BindView(R.id.frag_door_recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.frag_door_refresh)
    SwipeRefreshLayout refresh;
    int lastVisibleItem;
    List<DoorRecordBean> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_function_door, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
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
                    // TODO: 2018/10/31 开门人员列表加载
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
    protected DoorFragmentPresenter createPresenter() {
        return new DoorFragmentPresenter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        list.clear();
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
                recyclerView.setAdapter(null);
                for (int i = 0; i < 11; i++) {
                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    DoorRecordBean bean = new DoorRecordBean();
                    bean.setName("开门人员" + i);
                    bean.setTime("2018-08-0" + i + " 13:00:00");
                    list.add(bean);

                }
                if (getActivity() == null) {
                    return;
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setRefresh(false);
                        recyclerView.setAdapter(adapter);
                        refresh.setRefreshing(false);
                    }
                });
            }
        }).start();
// TODO: 2018/10/31 开门人员列表刷新请求
    }

    @Override
    public void selected() {
        if (list != null && list.size() == 0 && !refresh.isRefreshing()) {
            onRefresh();
        }
    }
}
