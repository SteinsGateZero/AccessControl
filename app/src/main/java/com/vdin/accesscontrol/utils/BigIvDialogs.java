package com.vdin.accesscontrol.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.vdin.accesscontrol.R;

public class BigIvDialogs extends Dialog implements View.OnClickListener {

    private String url;
    private ImageView bigImg;
    private LinearLayout layout;

    public BigIvDialogs(String url, @NonNull Context context) {
        super(context, R.style.big_img_dialog);
        this.url = url;
    }

    public BigIvDialogs(@NonNull Context context) {
        super(context);
    }

    public BigIvDialogs(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BigIvDialogs(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        layout = new LinearLayout(getContext());
        setContentView(layout);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        setUrl(url);
    }

    public void setUrl(String url) {
        this.url = url;
        layout.removeAllViews();
        bigImg = new ImageView(getContext());
        layout.addView(bigImg);
        bigImg.setOnClickListener(this);
        Glide.with(getContext()).load(url).into(bigImg);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
