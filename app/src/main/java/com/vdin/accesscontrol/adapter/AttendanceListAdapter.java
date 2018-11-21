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
import com.vdin.accesscontrol.model.bean.AttendanceBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by new1 on 2018/10/31.
 * 开门人员列表适配器
 */

public class AttendanceListAdapter extends BaseEmptyListAdapter<AttendanceBean> {

    public AttendanceListAdapter(@NonNull List<AttendanceBean> list,SwipeRefreshLayout.OnRefreshListener refreshListener) {
        super(list, R.mipmap.qs_record,"暂无记录",refreshListener);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //先通过list长度判定占位视图
        if (i == LIST_EMPTY) {
            return super.onCreateViewHolder(viewGroup);
        }
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_function_attendance, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).name.setText(list.get(i).getName());
            ((MyViewHolder) holder).dutyTime.setText(list.get(i).getTime1());
            ((MyViewHolder) holder).offDutyTime.setText(list.get(i).getTime2());
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adp_attendance_img)
        ImageView img;
        @BindView(R.id.adp_attendance_name)
        TextView name;
        @BindView(R.id.adp_attendance_dutyTime)
        TextView dutyTime;
        @BindView(R.id.adp_attendance_offDutyTime)
        TextView offDutyTime;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
