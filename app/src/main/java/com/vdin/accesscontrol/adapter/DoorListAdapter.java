package com.vdin.accesscontrol.adapter;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.vdin.accesscontrol.R;
import com.vdin.accesscontrol.model.bean.DoorListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by new1 on 2018/10/31.
 * 门禁列表
 */

public class DoorListAdapter extends BaseEmptyListAdapter<DoorListBean> {

    public DoorListAdapter(@NonNull List<DoorListBean> list, SwipeRefreshLayout.OnRefreshListener refreshListener) {
        super(list, R.mipmap.qs_equipment, "暂无设备,请在后台添加设备", refreshListener);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == LIST_EMPTY) {
            return super.onCreateViewHolder(viewGroup);
        }
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_door, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof MyViewHolder) {
            MyViewHolder holder = (MyViewHolder) viewHolder;
            holder.name.setText(list.get(i).getName());
            if (list.get(i).isOnline()) {
                holder.img.setImageResource(R.mipmap.icon_guard2);
                holder.offline.setVisibility(View.GONE);
                holder.openDoor.setVisibility(View.VISIBLE);
            } else {
                holder.img.setImageResource(R.mipmap.icon_offlineguard);
                holder.offline.setVisibility(View.VISIBLE);
                holder.openDoor.setVisibility(View.GONE);
            }
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adp_doorList_name)
        TextView name;
        @BindView(R.id.adp_doorList_img)
        ImageView img;
        @BindView(R.id.adp_doorList_btn)
        Button openDoor;
        @BindView(R.id.adp_doorList_offline)
        TextView offline;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
