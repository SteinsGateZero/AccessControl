package com.vdin.accesscontrol.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.Group;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.vdin.accesscontrol.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by new1 on 2018/11/2.
 * 空展位图列表适配器
 */

public abstract class BaseEmptyListAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected List<T> list;
    static final int LIST_EMPTY = 0;
    static final int LIST_NOT_EMPTY = 1;
    private int emptyRes;
    private String tips;
    private EmptyViewHolder viewHolder;
    private SwipeRefreshLayout.OnRefreshListener refreshListener;
    private boolean isRefresh;

    BaseEmptyListAdapter(@NonNull List<T> list, int emptyRes, String tips, @NonNull SwipeRefreshLayout.OnRefreshListener refreshListener) {
        this.list = list;
        this.emptyRes = emptyRes;
        this.tips = tips;
        this.refreshListener = refreshListener;
    }

    /**
     * 网络问题时的占位视图
     */
    public void setNoNetWork() {
        if (viewHolder.emptyImg.getVisibility() == View.GONE) {
            viewHolder.emptyImg.setVisibility(View.VISIBLE);
            viewHolder.emptyTips.setVisibility(View.VISIBLE);
            viewHolder.group.setVisibility(View.GONE);
        }
        viewHolder.emptyImg.setImageResource(R.mipmap.qs_network);
        viewHolder.emptyTips.setText("矮油,外太空没有网络~");
        viewHolder.emptyBtn.setVisibility(View.VISIBLE);

        notifyDataSetChanged();
    }

    /**
     * 没有数据时的占位视图
     */
    public void setNoList() {
        if (viewHolder.emptyImg.getVisibility() == View.GONE) {
            viewHolder.emptyImg.setVisibility(View.VISIBLE);
            viewHolder.emptyTips.setVisibility(View.VISIBLE);
            viewHolder.group.setVisibility(View.GONE);
        }
        viewHolder.emptyImg.setImageResource(emptyRes);
        viewHolder.emptyTips.setText(tips);
        viewHolder.emptyBtn.setVisibility(View.GONE);
        notifyDataSetChanged();
    }

    /**
     * 分组列表空占位图
     */
    public void setNoGroupList() {
        viewHolder.emptyImg.setVisibility(View.GONE);
        viewHolder.emptyTips.setVisibility(View.GONE);
        viewHolder.emptyBtn.setVisibility(View.GONE);
        viewHolder.group.setVisibility(View.VISIBLE);
        notifyDataSetChanged();
    }

    /**
     * 刷新完成时让网络问题视图中的按钮可点击
     */
    public void setRefresh(boolean isRefresh) {
        this.isRefresh = isRefresh;
    }

    RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.base_empty, viewGroup, false);
        viewHolder = new EmptyViewHolder(itemView);
        if (emptyRes == 0 && tips == null) {
            viewHolder.group.setVisibility(View.VISIBLE);
            return viewHolder;
        }
        viewHolder.emptyImg.setImageResource(emptyRes);
        viewHolder.emptyTips.setText(tips);
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        return list.size() == 0 ? LIST_EMPTY : LIST_NOT_EMPTY;
    }

    @Override
    public int getItemCount() {
        return list.size() == 0 ? 1 : list.size();
    }

    class EmptyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.empty_img)
        ImageView emptyImg;
        @BindView(R.id.empty_tips)
        TextView emptyTips;
        @BindView(R.id.empty_btn)
        Button emptyBtn;
        @BindView(R.id.empty_add_btn)
        View addGroupBtn;
        @BindView(R.id.empty_group)
        Group group;

        EmptyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            emptyBtn.setOnClickListener(this);
            addGroupBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.empty_btn:
                    if (isRefresh) {
                        return;
                    }
                    refreshListener.onRefresh();
                    break;
                case R.id.empty_add_btn:
                    break;
            }
        }
    }
}
