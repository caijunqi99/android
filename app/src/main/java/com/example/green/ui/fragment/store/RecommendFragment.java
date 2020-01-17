package com.example.green.ui.fragment.store;


import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.green.R;
import com.example.green.adapter.store.MyStoreListAdapter;
import com.example.green.base.BaseMvpFragment;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.store.StoreListbean;
import com.example.green.config.ApiConfig;
import com.example.green.config.LoadConfig;
import com.example.green.model.StoreModel;
import com.example.green.ui.activity.store.StoreInfoActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendFragment extends BaseMvpFragment<CommonPresenter, StoreModel>
        implements ICommonView {

    @BindView(R.id.store_recyclerview)
    RecyclerView mRecyclerView;
    private String store_id;
    private int page = 1;
    private List<StoreListbean.ResultBean> mResultBeanList;
    private MyStoreListAdapter mMyStoreListAdapter;
    static RecommendFragment fragment;

    @SuppressLint("ValidFragment")
    public RecommendFragment(String pStoreclass_id) {
        store_id = pStoreclass_id;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    public RecommendFragment() {
        // Required empty public constructor
    }

    public static RecommendFragment newInstance() {
        if (fragment == null) fragment = new RecommendFragment();
        return fragment;
    }

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
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView() {
        mResultBeanList = new ArrayList<>();
        mMyStoreListAdapter = new MyStoreListAdapter(R.layout.layout_store_item, mResultBeanList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mMyStoreListAdapter);

        mMyStoreListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.store_card:
                    case R.id.go_store:
                        StoreInfoActivity.startInfoActivity(getActivity(), String.valueOf(mResultBeanList.get(position).getStore_id()));
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getData(ApiConfig.RECOMMEND_STORE, store_id, page, LoadConfig.NORMAL);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.RECOMMEND_STORE:
                StoreListbean storeListbean = (StoreListbean) t[0];
                if (null != storeListbean) {
                    List<StoreListbean.ResultBean> result = storeListbean.getResult();
                    mResultBeanList.addAll(result);
                    mMyStoreListAdapter.notifyDataSetChanged();
                }
                break;
        }
    }
}
