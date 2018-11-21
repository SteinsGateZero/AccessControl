package com.vdin.accesscontrol.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vdin.accesscontrol.R;
import com.vdin.accesscontrol.model.bean.MemberBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by new1 on 2018/11/20.
 * 分组人员列表适配器
 */

public class GroupMemberAdapter extends RecyclerView.Adapter<GroupMemberAdapter.MyViewHolder> {


    private List<MemberBean> list;
    private int group;

    private GroupMemberListener listener;

    public void notifyData(List<MemberBean> list, int group) {
        this.list = list;
        this.group = group;
        notifyDataSetChanged();
    }

    GroupMemberAdapter(List<MemberBean> list, int group, GroupMemberListener listener) {
        this.list = list;
        this.group = group;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_group_member, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        viewHolder.name.setText(list.get(i).getName());

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.adp_member_img)
        ImageView img;
        @BindView(R.id.adp_member_name)
        TextView name;
        @BindView(R.id.adp_member_btn)
        View btn;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            btn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(group, getAdapterPosition());
        }
    }

    /**
     * 成员点击监听
     */
    public interface GroupMemberListener {
        /**
         * @param group  所处的分组group索引
         * @param member 被点击成员的索引
         */
        void onClick(int group, int member);
    }
}
