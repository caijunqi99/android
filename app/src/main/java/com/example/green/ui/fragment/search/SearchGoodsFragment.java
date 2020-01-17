package com.example.green.ui.fragment.search;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.green.R;
import com.example.green.adapter.homepage.MySearchListAdapter;
import com.example.green.base.BaseMvpFragment;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.homepage.SearchListbean;
import com.example.green.config.ApiConfig;
import com.example.green.config.LoadConfig;
import com.example.green.model.HomePageModel;
import com.example.green.ui.activity.GoodsDetailsActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class SearchGoodsFragment extends BaseMvpFragment<CommonPresenter, HomePageModel> implements ICommonView {
    @BindView(R.id.SmartRefresh)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.goods_recycler)
    RecyclerView mRecyclerView;
    private int KEY_STYLE;// 0为默认综合排序，2为价格排序，3销量排序，5人气排序
    private String keyWord;
    private String gcId;
    private int page;
    private static final String TAG = "SearchGoodsFragment";
    private MySearchListAdapter mMySearchListAdapter;
    private List<SearchListbean.ResultBean.GoodsListBean> mGoodsListBeans;
    private SearchListbean mSearchListbean;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    public static SearchGoodsFragment newInstance(String pKeyWord, String pGcId, int pKEY_STYLE) {
        SearchGoodsFragment myFragment = new SearchGoodsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("keyWord", pKeyWord);
        bundle.putString("gcId", pGcId);
        bundle.putInt("key", pKEY_STYLE);
        myFragment.setArguments(bundle);
        return myFragment;
    }

    @Override
    protected CommonPresenter initPresenter() {
        return new CommonPresenter();
    }

    @Override
    protected HomePageModel initModel() {
        return new HomePageModel();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search_goods;
    }

    @Override
    protected void initView() {
        Bundle bundle = getArguments();
        if (null != bundle) {
            keyWord = bundle.getString("keyWord");
            gcId = bundle.getString("gcId");
            KEY_STYLE = bundle.getInt("key");
        }
        mGoodsListBeans = new ArrayList<>();
        mMySearchListAdapter = new MySearchListAdapter(R.layout.layout_search_list_item, mGoodsListBeans);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mMySearchListAdapter);

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                mPresenter.getData(ApiConfig.SEARCH_GOODS, keyWord, page, KEY_STYLE, gcId, LoadConfig.REFRESH);
            }
        });
        mSmartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (mSearchListbean.isHasmore()) {
                    mPresenter.getData(ApiConfig.SEARCH_GOODS, keyWord, ++page, KEY_STYLE, gcId, LoadConfig.LOADMORE);
                } else {
                    mSmartRefreshLayout.finishLoadmore();
                }
            }
        });

        mMySearchListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.rl_goods:
                        Intent intent = new Intent(getContext(), GoodsDetailsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("goodsId", mGoodsListBeans.get(position).getGoods_id() + "");
                        intent.putExtras(bundle);
                        getActivity().startActivity(intent);
                        break;
                    /*case R.id.add_cart: // 添加到购物车
                        break;*/
                }
            }
        });
    }

    @Override
    protected void initData() {
        page = 1;
        mPresenter.getData(ApiConfig.SEARCH_GOODS, keyWord, page, KEY_STYLE, gcId, LoadConfig.NORMAL);
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError: " + e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.SEARCH_GOODS:
                mSearchListbean = (SearchListbean) t[0];
                if (null != mSearchListbean) {
                    List<SearchListbean.ResultBean.GoodsListBean> goods_list = mSearchListbean.getResult().getGoods_list();
                    int loadMode = (int) t[1];
                    if (loadMode == LoadConfig.NORMAL) {
                        mGoodsListBeans.addAll(goods_list);
                    } else if (loadMode == LoadConfig.REFRESH) {
                        mGoodsListBeans.clear();
                        mGoodsListBeans.addAll(goods_list);
                        mSmartRefreshLayout.finishRefresh();
                    } else if (loadMode == LoadConfig.LOADMORE) {
                        mGoodsListBeans.addAll(goods_list);
                        mSmartRefreshLayout.finishLoadmore();
                    }
                    mMySearchListAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    // 接收广播
    protected void receiverBroadCast(Intent intent) {
        super.receiverBroadCast(intent);
        switch (intent.getAction()) {
            case SEARCH_SUCCESS: // 搜索成功
                mPresenter.getData(ApiConfig.SEARCH_GOODS, keyWord, page, KEY_STYLE, gcId, LoadConfig.REFRESH);
                Log.e(TAG, "receiverBroadCast: " + keyWord);
                break;
        }
    }
}
