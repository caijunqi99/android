package com.example.green.ui.activity.homepage;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.green.R;
import com.example.green.adapter.homepage.MySystemMessageAdapter;
import com.example.green.base.BaseMvpActivity;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.homepage.SystemMessageListbean;
import com.example.green.config.ApiConfig;
import com.example.green.config.LoadConfig;
import com.example.green.local_utils.SPUtils;
import com.example.green.model.HomePageModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SystemMessageActivity extends BaseMvpActivity<CommonPresenter, HomePageModel>
        implements ICommonView {

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.SmartRefresh)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.message_recyclerview)
    RecyclerView mMessageRecyclerview;
    private List<SystemMessageListbean.ResultBean> messageList;
    private MySystemMessageAdapter mMySystemMessageAdapter;
    private String key;
    private int page;

    @Override
    protected void initView() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        key = SPUtils.getInstance().getValue(SPUtils.KEY_USER_TOKEN, "");
        messageList = new ArrayList<>();
        mMySystemMessageAdapter = new MySystemMessageAdapter(R.layout.layout_message_item, messageList);
        mMessageRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mMessageRecyclerview.setAdapter(mMySystemMessageAdapter);

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                mPresenter.getData(ApiConfig.SYSTEM_MESSAGE, key, page, LoadConfig.REFRESH);
            }
        });
        mSmartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.getData(ApiConfig.SYSTEM_MESSAGE, key, ++page, LoadConfig.LOADMORE);
            }
        });
    }

    @Override
    protected void initData() {
        page = 1;
        mPresenter.getData(ApiConfig.SYSTEM_MESSAGE, key, page, LoadConfig.NORMAL);
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
        return R.layout.activity_system_message;
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.SYSTEM_MESSAGE:
                SystemMessageListbean systemMessageListbean = (SystemMessageListbean) t[0];
                if (null != systemMessageListbean && systemMessageListbean.getCode().equals("200")) {
                    List<SystemMessageListbean.ResultBean> result = systemMessageListbean.getResult();
                    int LoadMode = (int) t[1];
                    if (LoadMode == LoadConfig.NORMAL) {
                        messageList.addAll(result);
                    } else if (LoadMode == LoadConfig.REFRESH) {
                        messageList.clear();
                        messageList.addAll(result);
                        mSmartRefreshLayout.finishRefresh();
                    } else if (LoadMode == LoadConfig.LOADMORE) {
                        messageList.addAll(result);
                        mSmartRefreshLayout.finishLoadmore();
                    }

                    mMySystemMessageAdapter.notifyDataSetChanged();
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
