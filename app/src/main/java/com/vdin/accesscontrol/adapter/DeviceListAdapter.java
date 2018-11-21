package com.vdin.accesscontrol.adapter;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vdin.accesscontrol.R;
import com.vdin.accesscontrol.model.bean.DeviceListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by new1 on 2018/11/12.
 * 显示设备列表
 */

public class DeviceListAdapter extends BaseEmptyListAdapter<DeviceListBean> {

    public DeviceListAdapter(@NonNull List<DeviceListBean> list, SwipeRefreshLayout.OnRefreshListener refreshListener) {
        super(list, R.mipmap.qs_equipment, "暂无设备,请在后台添加设备", refreshListener);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == LIST_EMPTY) {
            return super.onCreateViewHolder(viewGroup);
        }
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_device, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof MyViewHolder) {
            MyViewHolder holder = (MyViewHolder) viewHolder;
            holder.name.setText(list.get(i).getDeviceName());
            if (list.get(i).isOnline()) {
                holder.img.setImageResource(R.mipmap.icon_online_projector);
                holder.offline.setVisibility(View.GONE);
            } else {
                holder.img.setImageResource(R.mipmap.icon_offline_projector);
                holder.offline.setVisibility(View.VISIBLE);
            }
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adp_deviceList_name)
        TextView name;
        @BindView(R.id.adp_deviceList_img)
        ImageView img;
        @BindView(R.id.adp_deviceList_btn)
        TextView offline;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
