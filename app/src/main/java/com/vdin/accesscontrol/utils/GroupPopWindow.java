package com.vdin.accesscontrol.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.vdin.accesscontrol.R;

/**
 * Created by new1 on 2018/11/14.
 * group主界面弹窗
 */

public class GroupPopWindow extends PopupWindow {

    private Context context;
    private View.OnClickListener listener;

    public GroupPopWindow(Context context, View.OnClickListener listener) {
        super(context);
        this.context = context;
        this.listener = listener;
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pop_group, null, false);
        view.findViewById(R.id.pop_btn_create_group).setOnClickListener(listener);
        view.findViewById(R.id.pop_btn_create_person).setOnClickListener(listener);
        view.findViewById(R.id.pop_btn_edit_group).setOnClickListener(listener);
        setContentView(view);
        initWindow();
    }

    private void initWindow() {
        this.setWidth(DpUtils.dpToPx(142));
        this.setHeight(DpUtils.dpToPx(159));
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void showAtBottom(View view) {
        //弹窗位置设置
        showAsDropDown(view, Math.abs((view.getWidth() - getWidth())) / 2, 0);
    }
}
