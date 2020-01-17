package com.example.green.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.green.R;
import com.example.green.customs.LoadingDialogWithContent;
import com.example.green.local_utils.StatusBarManager;

public class BaseActivity extends AppCompatActivity {

    // 更新头像，修改昵称
    public static final String RECTIFY_UPDATE_INFO = "rectify_update_info";

    // 提交订单成功
    public static final String SUBMIT_ORDER = "submit_order";
    // 添加收货地址
    public static final String ADD_ADDRESS = "add_address";
    // 编辑收货地址
    public static final String EDITOR_ADDRESS = "editor_address";
    // 删除收货地址
    public static final String DELETE_ADDRESS = "delete_address";
    // 搜索列表刷新
    public static final String SEARCH_SUCCESS = "search_success";
    // 付款成功
    public static final String PAY_SUCCESS = "pay_success";
    // 修改订单状态
    public static final String CHANGE_ORDER_STATE = "change_order_state";
    // 成功互转
    public static final String TRANSFORM_SUCCESS = "transform_success";
    // 充值成功
    public static final String RECHARGE_SUCCESS = "recharge_success";


    private MyApplication application;
    private LoadingDialogWithContent mDialog;

    private UserBroadCastReceiver mUserBroadCastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 获取userId Token
        application = (MyApplication) getApplication();
        Log.e("-------绿色长城-------", this.getClass().getSimpleName());
        mDialog = new LoadingDialogWithContent(this, false, getString(R.string.Info));

        mUserBroadCastReceiver = new UserBroadCastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(RECTIFY_UPDATE_INFO);
        intentFilter.addAction(SUBMIT_ORDER);
        intentFilter.addAction(ADD_ADDRESS);
        intentFilter.addAction(EDITOR_ADDRESS);
        intentFilter.addAction(DELETE_ADDRESS);
        intentFilter.addAction(SEARCH_SUCCESS);
        intentFilter.addAction(PAY_SUCCESS);
        intentFilter.addAction(CHANGE_ORDER_STATE);
        intentFilter.addAction(TRANSFORM_SUCCESS);
        intentFilter.addAction(RECHARGE_SUCCESS);

        initBroadCastReceiver(intentFilter);
        LocalBroadcastManager.getInstance(this).registerReceiver(mUserBroadCastReceiver, intentFilter);
    }


    public void showLoadingDialog() {
        if (!mDialog.isShowing()) mDialog.show();
    }

    public void hideLoadingDialog() {
        if (mDialog.isShowing()) mDialog.dismiss();
    }

    public void showToast(String content) {// 2.5s
        Toast.makeText(getApplicationContext(), "" + content, Toast.LENGTH_SHORT).show();
    }


    public void showLongToast(String content) { // 3.5s
        Toast.makeText(getApplicationContext(), content, Toast.LENGTH_LONG).show();
    }

    /**
     * @param color 沉浸式状态栏颜色
     */
    public void setStatusBarColor(int color) {
        StatusBarManager.setStatusBarColor(this, color);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUserBroadCastReceiver != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(mUserBroadCastReceiver);
        }
    }

    private class UserBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            receiverBroadCast(intent);
        }
    }

    protected void initBroadCastReceiver(IntentFilter intentFilter) {
    }

    protected void receiverBroadCast(Intent intent) {
    }

}
