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
import com.vdin.accesscontrol.adapter.AttendanceListAdapter;
import com.vdin.accesscontrol.base.netbase.NetBaseFragment;
import com.vdin.accesscontrol.contract.AttendanceFragmentContract;
import com.vdin.accesscontrol.model.AttendanceFragmentModel;
import com.vdin.accesscontrol.model.bean.AttendanceBean;
import com.vdin.accesscontrol.presenter.AttendanceFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by new1 on 2018/10/29.
 * 考勤人员列表界面
 */

public class AttendanceFragment extends NetBaseFragment<AttendanceFragmentModel, AttendanceFragmentContract.IAttendanceFragmentView, AttendanceFragmentPresenter> implements AttendanceFragmentContract.IAttendanceFragmentView, SwipeRefreshLayout.OnRefreshListener {

    private AttendanceListAdapter adapter;
    @BindView(R.id.frag_attendance_recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.frag_attendance_refresh)
    SwipeRefreshLayout refresh;
    private List<AttendanceBean> list;
    private int lastVisibleItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_function_attendance, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    @Override
    protected AttendanceFragmentPresenter createPresenter() {
        return new AttendanceFragmentPresenter();
    }

    private void init() {
        list = new ArrayList<>();
        adapter = new AttendanceListAdapter(list, this);
        recyclerView.setAdapter(adapter);
        refresh.setColorSchemeResources(android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        refresh.setOnRefreshListener(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //判断RecyclerView的状态是空闲时,同时是最后一个可见的ITEM时才加载
                if (!refresh.isRefreshing() && newState == RecyclerView.SCROLL_STATE_IDLE && list.size() != 0 && lastVisibleItem + 1 == adapter.getItemCount()) {
                    // TODO: 2018/10/31 考勤人员列表加载
                    list.clear();
                    adapter.setNoNetWork();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    AttendanceBean bean = new AttendanceBean();
                    bean.setName("考勤" + i);
                    bean.setTime1("09:00");
                    bean.setTime2("14:00");
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
// TODO: 2018/10/31 考勤人员列表刷新请求
    }

    @Override
    public void selected() {
        //空数据进行加载刷新
        if (list.size() == 0 && !refresh.isRefreshing()) {
            onRefresh();
        }
    }
}
