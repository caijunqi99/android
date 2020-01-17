package com.example.green.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.green.R;
import com.example.green.local_utils.StatusBarManager;

public class BaseFragment extends Fragment {

    private UserBroadCastReceiver mUserBroadCastReceiver;
    //更新头像，修改昵称
    public static final String RECTIFY_UPDATE_INFO = "rectify_update_info";
    // 搜索成功
    public static final String SEARCH_SUCCESS = "search_success";
    // 支付成功
    public static final String PAY_SUCCESS = "pay_success";
    // 修改订单状态
    public static final String CHANGE_ORDER_STATE = "change_order_state";
    // 提交订单成功
    public static final String SUBMIT_ORDER = "submit_order";

    public int mAppColor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mAppColor = ContextCompat.getColor(getContext(), R.color.app_theme_color);

        mUserBroadCastReceiver = new UserBroadCastReceiver();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(RECTIFY_UPDATE_INFO);
        intentFilter.addAction(SEARCH_SUCCESS);
        intentFilter.addAction(PAY_SUCCESS);
        intentFilter.addAction(CHANGE_ORDER_STATE);
        intentFilter.addAction(SUBMIT_ORDER);
        initBroadCastReceiver(intentFilter);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mUserBroadCastReceiver, intentFilter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    public void refresh() {
    }

    public void loadMore() {
    }


    public void showToast(String content) {// 2.5s
        Toast.makeText(getContext().getApplicationContext(), content, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(String content) { // 3.5s
        Toast.makeText(getContext().getApplicationContext(), content, Toast.LENGTH_LONG).show();
    }

    public void setStatusBarColor(int color) {
        StatusBarManager.setStatusBarColor(getActivity(), color);
    }

    public void showLog(String content) {
        Log.e("睚眦必报", content);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUserBroadCastReceiver != null) {
            LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mUserBroadCastReceiver);
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
