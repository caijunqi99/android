package com.example.green.local_utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Fly on 2017/11/25.
 */

public class NoScrollViewPager extends ViewPager {


    public NoScrollViewPager(@NonNull Context context) {
        super(context);
    }

    public NoScrollViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return false;//不拦截事件，让嵌套的子viewpager有机会响应触摸事件
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        // 重写ViewPager滑动事件，改什么都不做
        return true;
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item, false);//表示切换的时候，不需要切换时间。
    }
}