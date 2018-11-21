package com.vdin.accesscontrol.base;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.vdin.accesscontrol.R;

public abstract class CommonTitleActivity extends AppCompatActivity {

    private TextView commonArrowTv;
    private TextView commonRightTv;

    protected void setBackArrow() {
        initArrow();
        if (commonArrowTv.hasOnClickListeners()) {
            return;
        }
        commonArrowTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    protected void setBackArrow(String arrowMsg) {
        setBackArrow();
        commonArrowTv.setText(arrowMsg);
    }

    protected void setBackArrow(View.OnClickListener listener) {
        initArrow();
        commonArrowTv.setOnClickListener(listener);
    }

    protected void setBackArrow(String arrowMsg, View.OnClickListener listener) {
        setBackArrow(listener);
        commonArrowTv.setText(arrowMsg);
    }

    protected void setRightText(String rightText, @NonNull View.OnClickListener listener) {
        commonRightTv.setText(rightText);
        commonRightTv.setVisibility(View.VISIBLE);
        commonRightTv.setOnClickListener(listener);
    }

    protected void setRightImg(@NonNull Drawable res, @NonNull View.OnClickListener listener) {
        setRightText("", listener);
        commonRightTv.setCompoundDrawablesWithIntrinsicBounds(res, null, null, null);
    }

    private void initArrow() {
        if (commonArrowTv == null) {
            commonArrowTv = findViewById(R.id.titleBar_tv);
            commonRightTv = findViewById(R.id.titleBar_tv_right);
        }
    }

}
