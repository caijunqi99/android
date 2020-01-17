
package com.example.green.local_utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.green.R;

/**
 * 普通升级对话框
 * <p>
 * 按键都是定义好的
 */

public class UpdateBaseDialog extends Dialog implements View.OnClickListener {
    protected Context context;      // 上下文
    protected int layoutResID;      // 布局文件id

    protected TextView tvVersion;//版本号码
    protected TextView tvInfo;//版本信息
    protected TextView tvLater;//稍后更新
    protected TextView tvUpdate;//马上更新

    protected String mVersion;
    protected String mInfo;


    public UpdateBaseDialog(Context context, int layoutResID, String version, String info) {
        super(context, R.style.dialog_custom); //dialog的样式
        this.context = context;
        this.layoutResID = layoutResID;
        this.mVersion = version;
        this.mInfo = info;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置为居中
        window.setWindowAnimations(R.style.bottom_menu_animation); // 添加动画效果
        setContentView(layoutResID);

        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = display.getWidth() * 1; // 设置dialog宽度为屏幕宽度(满屏)
        getWindow().setAttributes(lp);
        setCanceledOnTouchOutside(false);// 点击Dialog外部消失
        //遍历控件id,添加点击事件
        tvVersion = findViewById(R.id.Versioncode);
        tvInfo = findViewById(R.id.tv_info);
        tvLater = findViewById(R.id.tv_later);
        tvUpdate = findViewById(R.id.tv_update);

        tvVersion.setText(mVersion + "");
        tvInfo.setText(mInfo + "");

        tvLater.setOnClickListener(this);
        tvUpdate.setOnClickListener(this);

    }

    private OnCenterItemClickListener listener;

    public interface OnCenterItemClickListener {
        void OnCenterItemClick(UpdateBaseDialog dialog, View view);
    }

    public void setOnCenterItemClickListener(OnCenterItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        listener.OnCenterItemClick(this, view);
    }
}
