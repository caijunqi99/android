/*
 * Copyright (c) 2017. Xi'an iRain IOT Technology service CO., Ltd (ShenZhen). All Rights Reserved.
 */
package com.example.green.local_utils.statusbar;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;


/**
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2017-11-21 0.7
 * @since 2017-11-21 0.7
 */
public class StatusBarView extends View {

    private int mStatusBarHeight;

    public StatusBarView(Context context) {
        this(context, null);
    }

    public StatusBarView(Context context,AttributeSet attrs) {
        super(context, attrs);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mStatusBarHeight = StatusBarTools.getStatusBarHeight(context);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), mStatusBarHeight);
    }
}
