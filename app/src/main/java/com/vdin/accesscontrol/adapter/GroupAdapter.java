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
import com.vdin.accesscontrol.model.bean.GroupBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by new1 on 2018/11/20.
 * 分组列表适配器
 */

public class GroupAdapter extends BaseEmptyListAdapter<GroupBean> {
    private GroupMemberAdapter.GroupMemberListener listener;

    public GroupAdapter(@NonNull List<GroupBean> list, @NonNull SwipeRefreshLayout.OnRefreshListener refreshListener, GroupMemberAdapter.GroupMemberListener listener) {
        super(list, 0, null, refreshListener);
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //先通过list长度判定占位视图
        if (i == LIST_EMPTY) {
            return super.onCreateViewHolder(viewGroup);
        }
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_group, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof MyViewHolder) {
            MyViewHolder holder = (MyViewHolder) viewHolder;
            GroupBean bean = list.get(i);
            if (bean.isSpread()) {
                holder.arrow.setImageResource(R.mipmap.icon_unfold);
                holder.recyclerView.setVisibility(View.VISIBLE);
                holder.line.setVisibility(View.VISIBLE);
                if (list.get(i).getList() != null) {
                    if (holder.adapter == null) {
                        holder.adapter = new GroupMemberAdapter(list.get(i).getList(), i, listener);
                        holder.recyclerView.setAdapter(holder.adapter);
                    } else {
                        holder.adapter.notifyData(list.get(i).getList(), i);
                    }
                }
            } else {
                holder.arrow.setImageResource(R.mipmap.icon_fold);
                holder.recyclerView.setVisibility(View.GONE);
                holder.line.setVisibility(View.GONE);
            }
            holder.name.setText(bean.getName());
            holder.num.setText(bean.getNum() + "人");
            holder.percent.setText("识别阈值:" + bean.getPercent());

        }

    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.group_name)
        TextView name;
        @BindView(R.id.group_percent)
        TextView percent;
        @BindView(R.id.group_num)
        TextView num;
        @BindView(R.id.group_arrow)
        ImageView arrow;
        @BindView(R.id.group_recyclerView)
        RecyclerView recyclerView;
        @BindView(R.id.group_fold_btn)
        View btn;
        @BindView(R.id.group_list_line)
        View line;
        GroupMemberAdapter adapter;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            btn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //展开与折叠
            boolean isSpread = list.get(getAdapterPosition()).isSpread();
            list.get(getAdapterPosition()).setSpread(!isSpread);

            if (!isSpread) {
                arrow.setImageResource(R.mipmap.icon_unfold);
                recyclerView.setVisibility(View.VISIBLE);
                line.setVisibility(View.VISIBLE);
                //判断是否初始化过adapter,若初始化过则加载当前的list内容,没有则先初始化
                if (adapter != null) {
                    adapter.notifyData(list.get(getAdapterPosition()).getList(), getAdapterPosition());
                    return;
                }
                adapter = new GroupMemberAdapter(list.get(getAdapterPosition()).getList(), getAdapterPosition(), listener);
                recyclerView.setAdapter(adapter);
            } else {
                arrow.setImageResource(R.mipmap.icon_fold);
                recyclerView.setVisibility(View.GONE);
                line.setVisibility(View.GONE);
            }

        }
    }
}
