package com.example.green.local_utils;

import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class TipsUtil {

    //PopupWindow的背景
    public static void setBackgroundAlpha(AppCompatActivity activity, float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        (activity).getWindow().setAttributes(lp);
    }

}