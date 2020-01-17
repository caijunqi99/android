package com.example.green.ui.activity.mine;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.green.R;
import com.example.green.adapter.mine.order.LogisticsItemAdapter;
import com.example.green.base.BaseMvpActivity;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.mine.LogisticsInfobean;
import com.example.green.config.ApiConfig;
import com.example.green.config.LoadConfig;
import com.example.green.local_utils.SPUtils;
import com.example.green.model.MineModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LogisticsActivity extends BaseMvpActivity<CommonPresenter, MineModel>
        implements ICommonView {

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.receiver_phone)
    TextView mReceiverPhone;
    @BindView(R.id.logistics_code)
    TextView mLogisticsCode;
    @BindView(R.id.expressage)
    TextView mExpressage;
    @BindView(R.id.logistics_recycler)
    RecyclerView mLogisticsRecycler;
    @BindView(R.id.SmartRefresh)
    SmartRefreshLayout mSmartRefresh;
    private String key;
    private String express_code;
    private String express_name;
    private String shipping_code;
    private String phone;
    private List<LogisticsInfobean.ResultBean> mLogisticsList;
    private LogisticsItemAdapter mLogisticsItemAdapter;

    @Override
    protected void initView() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        key = SPUtils.getInstance().getValue(SPUtils.KEY_USER_TOKEN, "");
        Intent intent = getIntent();
        express_code = intent.getStringExtra("express_code");
        express_name = intent.getStringExtra("express_name");
        shipping_code = intent.getStringExtra("shipping_code");
        phone = intent.getStringExtra("phone");
        mReceiverPhone.setText(phone);
        mLogisticsCode.setText(shipping_code);
        mExpressage.setText(express_name);

        mLogisticsList = new ArrayList<>();
        mLogisticsItemAdapter = new LogisticsItemAdapter(R.layout.layout_logistics_item, mLogisticsList);
        mLogisticsRecycler.setLayoutManager(new LinearLayoutManager(this));
        mLogisticsRecycler.setAdapter(mLogisticsItemAdapter);

        mSmartRefresh.setEnableLoadmore(false);
        mSmartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.getData(ApiConfig.GET_LOGISTICS, key, express_code, shipping_code, phone, LoadConfig.REFRESH);
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getData(ApiConfig.GET_LOGISTICS, key, express_code, shipping_code, phone, LoadConfig.NORMAL);
    }

    @Override
    protected MineModel initModel() {
        return new MineModel();
    }

    @Override
    protected CommonPresenter initPresenter() {
        return new CommonPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_logistics;
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.GET_LOGISTICS:
                LogisticsInfobean logisticsInfobean = (LogisticsInfobean) t[0];
                if (null != logisticsInfobean && logisticsInfobean.getCode().equals("200")) {

                    int load = (int) t[1];
                    if (load == LoadConfig.NORMAL) {
                        mLogisticsList.addAll(logisticsInfobean.getResult());
                    } else if (load == LoadConfig.REFRESH) {
                        mLogisticsList.clear();
                        mLogisticsList.addAll(logisticsInfobean.getResult());
                        mSmartRefresh.finishRefresh();
                    }
                    mLogisticsItemAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    @OnClick(R.id.back)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
