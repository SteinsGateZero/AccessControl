package com.vdin.accesscontrol.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxu.shadowdrawable.ShadowDrawable;
import com.vdin.accesscontrol.R;
import com.vdin.accesscontrol.model.bean.DoorRecordBean;
import com.vdin.accesscontrol.utils.BigIvDialogs;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.vdin.accesscontrol.utils.DpUtils.dpToPx;

/**
 * Created by new1 on 2018/10/31.
 * 开门人员列表适配器
 */

public class DoorRecordAdapter extends BaseEmptyListAdapter<DoorRecordBean> {
    private BigIvDialogs bigIvDialogs;

    public DoorRecordAdapter(@NonNull List<DoorRecordBean> list, SwipeRefreshLayout.OnRefreshListener refreshListener) {
        super(list, R.mipmap.qs_record, "暂无记录", refreshListener);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == LIST_EMPTY) {
            return super.onCreateViewHolder(viewGroup);
        }
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_function_door, viewGroup, false);
        ShadowDrawable.setShadowDrawable(itemView, Color.WHITE, viewGroup.getContext().getResources().getDimensionPixelOffset(R.dimen.badge_corner_radius),
                Color.parseColor("#26a4a4a4"), viewGroup.getContext().getResources().getDimensionPixelOffset(R.dimen.badge_corner_radius), 0, dpToPx(6));
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof MyViewHolder) {
            MyViewHolder holder = (MyViewHolder) viewHolder;
            holder.name.setText(list.get(i).getName());
            holder.time.setText(list.get(i).getTime());
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.adapter_fun_iv)
        ImageView img;
        @BindView(R.id.adapter_fun_name)
        TextView name;
        @BindView(R.id.adapter_fun_time)
        TextView time;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            img.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (bigIvDialogs == null) {
                bigIvDialogs = new BigIvDialogs("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1542619800251&di=62f74f59cfc5789d926019d81339e949&imgtype=0&src=http%3A%2F%2Fpic33.nipic.com%2F20130912%2F7428510_011905467000_2.jpg", v.getContext());
            } else {
                if (getAdapterPosition() % 2 == 0) {
                    bigIvDialogs.setUrl("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1300730059,3492795053&fm=26&gp=0.jpg");
                } else {
                    bigIvDialogs.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1542619800251&di=62f74f59cfc5789d926019d81339e949&imgtype=0&src=http%3A%2F%2Fpic33.nipic.com%2F20130912%2F7428510_011905467000_2.jpg");
                }
            }
            bigIvDialogs.show();
        }
    }
}
