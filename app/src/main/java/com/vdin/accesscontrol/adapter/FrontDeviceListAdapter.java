package com.vdin.accesscontrol.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.vdin.accesscontrol.R;
import com.vdin.accesscontrol.model.bean.FrontDeviceBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by new1 on 2018/11/12.
 * 前端设备列表
 */

public class FrontDeviceListAdapter extends BaseEmptyListAdapter<FrontDeviceBean> {

    private RequestOptions options = new RequestOptions()
            .placeholder(R.mipmap.pic_network)    //加载成功之前占位图
            .error(R.mipmap.pic_network)//加载错误之后的错误图
            .transform(new RoundedCorners(8));


    private Context context;
    private AdapterView.OnItemClickListener clickListener;

    public FrontDeviceListAdapter(@NonNull List<FrontDeviceBean> list, SwipeRefreshLayout.OnRefreshListener refreshListener, AdapterView.OnItemClickListener clickListener) {
        super(list, R.mipmap.qs_equipment, "暂无设备,请在后台添加设备", refreshListener);
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == LIST_EMPTY) {
            return super.onCreateViewHolder(viewGroup);
        }
        context = viewGroup.getContext();
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_frontdevice, viewGroup, false);
        return new MyViewHolder(itemView, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof MyViewHolder) {
            MyViewHolder holder = (MyViewHolder) viewHolder;
            holder.name.setText(list.get(i).getName());
            if (list.get(i).isOnline()) {
                Glide.with(context).load(list.get(i).getImg()).apply(options).into(holder.img);
                holder.offline.setVisibility(View.GONE);
            } else {
                holder.img.setImageResource(R.mipmap.pic_offline);
                holder.offline.setVisibility(View.VISIBLE);
                holder.offline.setText(list.get(i).getOffline());
            }
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.adp_frontDevice_name)
        TextView name;
        @BindView(R.id.adp_frontDevice_offline_tv)
        TextView offline;
        @BindView(R.id.adp_frontDevice_img)
        ImageView img;

        private AdapterView.OnItemClickListener clickListener;

        MyViewHolder(View view, AdapterView.OnItemClickListener clickListener) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
            this.clickListener = clickListener;
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onItemClick(null, v, getAdapterPosition(), getItemId());
            }
        }
    }
}
