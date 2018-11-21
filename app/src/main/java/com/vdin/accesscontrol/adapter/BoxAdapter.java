package com.vdin.accesscontrol.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Group;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxu.shadowdrawable.ShadowDrawable;
import com.vdin.accesscontrol.R;
import com.vdin.accesscontrol.model.bean.BoxBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.vdin.accesscontrol.utils.DpUtils.dpToPx;

/**
 * Created by new1 on 2018/11/5.
 * 盒子列表
 */

public class BoxAdapter extends BaseEmptyListAdapter<BoxBean> {

    private BoxListener listener;

    public BoxAdapter(@NonNull List<BoxBean> list, @NonNull SwipeRefreshLayout.OnRefreshListener refreshListener, @NonNull BoxListener listener) {
        super(list, R.mipmap.qs_hezi, "暂无盒子,请先激活盒子", refreshListener);
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == LIST_EMPTY) {
            return super.onCreateViewHolder(viewGroup);
        }
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_box, viewGroup, false);
        //阴影效果
        ShadowDrawable.setShadowDrawable(itemView, Color.WHITE, viewGroup.getContext().getResources().getDimensionPixelOffset(R.dimen.badge_corner_radius),
                Color.parseColor("#26a4a4a4"), viewGroup.getContext().getResources().getDimensionPixelOffset(R.dimen.badge_corner_radius), 0, dpToPx(6));
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof MyViewHolder) {
            MyViewHolder holder = (MyViewHolder) viewHolder;
            BoxBean boxBean = list.get(i);
            holder.boxName.setText(boxBean.getName());
            //判断展开
            if (boxBean.isSpread()) {
                holder.arrow.setImageResource(R.mipmap.icon_unfold);
                holder.boxGroup.setVisibility(View.VISIBLE);
            } else {
                holder.arrow.setImageResource(R.mipmap.icon_fold);
                holder.boxGroup.setVisibility(View.GONE);
            }

            holder.deviceNum.setText(String.valueOf(boxBean.getDevice()));
            holder.doorNum.setText(String.valueOf(boxBean.getDoor()));
            holder.screenNum.setText(String.valueOf(boxBean.getScreen()));
        }

    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.adp_box_name)
        TextView boxName;
        @BindView(R.id.adp_box_arrow)
        ImageView arrow;
        @BindView(R.id.adp_box_doorNum)
        TextView doorNum;
        @BindView(R.id.adp_box_door_layout)
        ConstraintLayout doorLayout;
        @BindView(R.id.adp_box_deviceNum)
        TextView deviceNum;
        @BindView(R.id.adp_box_device_layout)
        ConstraintLayout deviceLayout;
        @BindView(R.id.adp_box_screenNum)
        TextView screenNum;
        @BindView(R.id.adp_box_screen_layout)
        ConstraintLayout screenLayout;
        @BindView(R.id.adp_box_net)
        TextView boxNet;
        @BindView(R.id.adp_box_save)
        TextView boxSave;
        @BindView(R.id.adp_box_theme)
        TextView boxTheme;
        @BindView(R.id.adp_box_group)
        Group boxGroup;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            arrow.setOnClickListener(this);
            doorLayout.setOnClickListener(this);
            deviceLayout.setOnClickListener(this);
            screenLayout.setOnClickListener(this);
            boxNet.setOnClickListener(this);
            boxSave.setOnClickListener(this);
            boxTheme.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.adp_box_arrow:
                    boolean isSpread = list.get(getAdapterPosition()).isSpread();
                    list.get(getAdapterPosition()).setSpread(!isSpread);
                    if (!isSpread) {
                        arrow.setImageResource(R.mipmap.icon_unfold);
                        boxGroup.setVisibility(View.VISIBLE);
                    } else {
                        arrow.setImageResource(R.mipmap.icon_fold);
                        boxGroup.setVisibility(View.GONE);
                    }
                    break;
                case R.id.adp_box_door_layout:
                    listener.doorDevice(getAdapterPosition());
                    break;
                case R.id.adp_box_device_layout:
                    listener.device(getAdapterPosition());
                    break;
                case R.id.adp_box_screen_layout:
                    listener.screenDevice(getAdapterPosition());
                    break;
                case R.id.adp_box_net:
                    listener.localNet(getAdapterPosition());
                    break;
                case R.id.adp_box_save:
                    listener.backUp(getAdapterPosition());
                    break;
                case R.id.adp_box_theme:
                    listener.boxTheme(getAdapterPosition());
                    break;
            }
        }
    }

    /**
     * 分开命名不同点击回调接口
     */
    public interface BoxListener {
        /**
         * @param position 当前item索引
         *                 门禁设备点击回调
         */
        void doorDevice(int position);

        /**
         * @param position 当前item索引
         *                 前段设备点击回调
         */
        void device(int position);

        /**
         * @param position 当前item索引
         *                 显示设备点击回调
         */
        void screenDevice(int position);

        /**
         * @param position 当前item索引
         *                 盒子网络点击回调
         */
        void localNet(int position);

        /**
         * @param position 当前item索引
         *                 云备份点击回调
         */
        void backUp(int position);

        /**
         * @param position 当前item索引
         *                 主题点击回调
         */
        void boxTheme(int position);

    }
}
