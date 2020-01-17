package com.example.green.ui.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;

import com.androidkun.xtablayout.XTabLayout;
import com.example.green.R;
import com.example.green.adapter.store.MyFragmentAdapter;
import com.example.green.base.BaseMvpFragment;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.model.StoreModel;
import com.example.green.ui.activity.SearchActivity;
import com.example.green.ui.fragment.store.RecommendFragment;
import com.example.green.ui.fragment.store.StoreclassifyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreFragment extends BaseMvpFragment<CommonPresenter, StoreModel>
        implements ICommonView {

    static StoreFragment fragment;
    private List<Fragment> mFragments;

    @BindView(R.id.tab)
    XTabLayout mTab;
    @BindView(R.id.search_store)
    RelativeLayout mSearchStore;
    @BindView(R.id.vp)
    ViewPager mVp;
    private MyFragmentAdapter mMyFragmentAdapter;


    public static StoreFragment newInstance() {
        if (fragment == null) fragment = new StoreFragment();
        return fragment;
    }

    public StoreFragment() {
        // Required empty public constructor
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
        return R.layout.fragment_store;
    }

    @Override
    protected void initView() {
        mFragments = new ArrayList<>();
        mFragments.add(RecommendFragment.newInstance());
        mFragments.add(StoreclassifyFragment.newInstance());
        mMyFragmentAdapter = new MyFragmentAdapter(getChildFragmentManager(), mFragments);
        mTab.addTab(mTab.newTab().setText("推荐店铺"));
        mTab.addTab(mTab.newTab().setText("店铺分类"));
        mVp.setAdapter(mMyFragmentAdapter);
        mTab.addOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(XTabLayout.Tab tab) {
                mVp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(XTabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(XTabLayout.Tab tab) {

            }
        });
        mVp.addOnPageChangeListener(new XTabLayout.TabLayoutOnPageChangeListener(mTab));
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }


    @OnClick(R.id.search_store)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.search_store:
                startActivity(new Intent(getContext(), SearchActivity.class));
                break;
        }
    }
}
