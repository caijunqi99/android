package com.example.green.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.green.R;
import com.example.green.base.BaseMvpActivity;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.local_utils.SPUtils;
import com.example.green.model.HomePageModel;
import com.example.green.ui.activity.homepage.LoginActivity;
import com.gyf.immersionbar.ImmersionBar;

import java.util.Timer;

public class SplashActivity extends BaseMvpActivity<CommonPresenter, HomePageModel>
        implements View.OnClickListener, ICommonView {

    private Handler handler;
    private Runnable runnable;
    private static final String TAG = "SplashActivity";
    @Override
    protected void initView() {
        ImmersionBar.with(this).init();
        gotoHomeActivity();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected HomePageModel initModel() {
        return new HomePageModel();
    }

    @Override
    protected CommonPresenter initPresenter() {
        return new CommonPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void onClick(View pView) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }

    private void gotoHomeActivity() {

        handler = new Handler();
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                String token = SPUtils.getInstance().getValue(SPUtils.KEY_USER_TOKEN, "");
                Log.e(TAG, "run: "+token );
                if (null != token) {
                    //从闪屏界面跳转到首界面
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                } else {
                    //从闪屏界面跳转到首界面
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }
            }
        }, 3000);//延迟3S后发送handler信息
    }
}
