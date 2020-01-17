package com.example.green.ui.fragment.store;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.green.R;
import com.example.green.adapter.store.MyAllStoreListAdapter;
import com.example.green.base.BaseMvpFragment;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.store.AllStoreListbean;
import com.example.green.config.ApiConfig;
import com.example.green.config.LoadConfig;
import com.example.green.model.StoreModel;
import com.example.green.ui.activity.GoodsDetailsActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.yiyatech.utils.ext.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllGoodsFragment extends BaseMvpFragment<CommonPresenter, StoreModel>
        implements ICommonView {

    static AllGoodsFragment fragment;
    @BindView(R.id.allGoods_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.SmartRefresh)
    SmartRefreshLayout mSmartRefreshLayout;
    private List<AllStoreListbean.ResultBean.GoodsListBean> mGoodsListBeans;
    private MyAllStoreListAdapter mAllStoreListAdapter;
    private String mStoreId;
    private int page = 1;

    public AllGoodsFragment() {
        // Required empty public constructor
    }

/*    public static AllGoodsFragment newInstance() {
        if (fragment == null) {
            fragment = new AllGoodsFragment();
        }
        return fragment;
    }*/


    @Override
    protected CommonPresenter initPresenter() {
        return new CommonPresenter();
    }

    @Override
    protected StoreModel initModel() {
        return new StoreModel();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_all_goods;
    }

    @Override
    protected void initView() {
        Bundle bundle = getArguments();
        mStoreId = bundle.getString("storeId");
        mGoodsListBeans = new ArrayList<>();
        mAllStoreListAdapter = new MyAllStoreListAdapter(R.layout.layout_store_recommend_item, mGoodsListBeans);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mRecyclerView.setAdapter(mAllStoreListAdapter);

        mAllStoreListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
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
                }
            }
        });

        mSmartRefreshLayout.setEnableRefresh(false);
        mSmartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (null != mGoodsListBeans) {
                    mPresenter.getData(ApiConfig.ALL_STOREGOODS, mStoreId, ++page, LoadConfig.LOADMORE);
                }
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getData(ApiConfig.ALL_STOREGOODS, mStoreId, page, LoadConfig.NORMAL);
    }

    @Override
    public void onError(Throwable e) {
        ToastUtils.show(getContext(), e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.ALL_STOREGOODS:
                AllStoreListbean allStoreListbean = (AllStoreListbean) t[0];
                if (null != allStoreListbean) {
                    List<AllStoreListbean.ResultBean.GoodsListBean>
                            goods_list = allStoreListbean.getResult().getGoods_list();
                    int loadType = (int) t[1]; // 加载方式
                    if (loadType == LoadConfig.NORMAL) {
                        mGoodsListBeans.addAll(goods_list);
                    } else if (loadType == LoadConfig.LOADMORE) {
                        mGoodsListBeans.addAll(goods_list);
                        mSmartRefreshLayout.finishLoadmore();
                    }
                    mAllStoreListAdapter.notifyDataSetChanged();
                }
                break;
        }
    }
}
