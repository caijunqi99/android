package com.example.green.ui.activity.mine.wallet;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.green.R;
import com.example.green.adapter.mine.wallet.WithdrawRecordItemAdapter;
import com.example.green.base.BaseMvpActivity;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.mine.wallet.WithdrawRecordListbean;
import com.example.green.config.ApiConfig;
import com.example.green.config.LoadConfig;
import com.example.green.local_utils.SPUtils;
import com.example.green.model.MineModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class WithdrawRecordActivity extends BaseMvpActivity<CommonPresenter, MineModel>
        implements ICommonView {

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.withdraw_recyclerview)
    RecyclerView mWithdrawRecyclerview;
    @BindView(R.id.SmartRefresh)
    SmartRefreshLayout mSmartRefresh;
    private String key;
    private int page;
    private int pageSize = 10;
    private WithdrawRecordListbean mWithdrawRecordListbean;
    private List<WithdrawRecordListbean.ResultBean.ListBean> mListBeans;
    private WithdrawRecordItemAdapter mWithdrawRecordItemAdapter;

    @Override
    protected void initView() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        key = SPUtils.getInstance().getValue(SPUtils.KEY_USER_TOKEN, "");
        mListBeans = new ArrayList<>();
        mWithdrawRecordItemAdapter = new WithdrawRecordItemAdapter(R.layout.withdraw_record_item, mListBeans);
        mWithdrawRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mWithdrawRecyclerview.setAdapter(mWithdrawRecordItemAdapter);

        mSmartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                mPresenter.getData(ApiConfig.WIRHDRAW_RECORD, key, page, pageSize, LoadConfig.REFRESH);
            }
        });
        mSmartRefresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (mWithdrawRecordListbean.isHasmore() == true) {
                    mPresenter.getData(ApiConfig.WIRHDRAW_RECORD, key, ++page, pageSize, LoadConfig.LOADMORE);
                } else {
                    mSmartRefresh.finishLoadmore();
                }
            }
        });
    }

    @Override
    protected void initData() {
        page = 1;
        mPresenter.getData(ApiConfig.WIRHDRAW_RECORD, key, page, pageSize, LoadConfig.NORMAL);
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
        return R.layout.activity_withdraw_record;
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.WIRHDRAW_RECORD:
                mWithdrawRecordListbean = (WithdrawRecordListbean) t[0];
                if (null != mWithdrawRecordListbean && mWithdrawRecordListbean.getCode().equals("200")) {
                    List<WithdrawRecordListbean.ResultBean.ListBean> list = mWithdrawRecordListbean.getResult().getList();
                    int loadMode = (int) t[1];
                    if (loadMode == LoadConfig.NORMAL) {
                        mListBeans.addAll(list);
                    } else if (loadMode == LoadConfig.REFRESH) {
                        mListBeans.clear();
                        mListBeans.addAll(list);
                        mSmartRefresh.finishRefresh();
                    } else if (loadMode == LoadConfig.LOADMORE) {
                        mListBeans.addAll(list);
                        mSmartRefresh.finishLoadmore();
                    }
                    mWithdrawRecordItemAdapter.notifyDataSetChanged();
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
