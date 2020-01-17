package com.example.green.ui.fragment.store;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.example.green.R;
import com.example.green.adapter.store.MyFragmentAdapter;
import com.example.green.base.BaseMvpFragment;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.store.StoreClassListbean;
import com.example.green.config.ApiConfig;
import com.example.green.config.LoadConfig;
import com.example.green.model.StoreModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreclassifyFragment extends BaseMvpFragment<CommonPresenter, StoreModel>
        implements ICommonView {

    @BindView(R.id.tab)
    XTabLayout mXTabLayout;
    @BindView(R.id.vp)
    ViewPager mVp;

    private List<Fragment> mFragments;
    private MyFragmentAdapter mMyFragmentAdapter;
    static StoreclassifyFragment fragment;

    public StoreclassifyFragment() {
        // Required empty public constructor
    }

    public static StoreclassifyFragment newInstance() {
        if (fragment == null) fragment = new StoreclassifyFragment();
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
        return R.layout.fragment_storeclassify;
    }

    @Override
    protected void initView() {
        mFragments = new ArrayList<>();
        mMyFragmentAdapter = new MyFragmentAdapter(getChildFragmentManager(), mFragments);
    }

    @Override
    protected void initData() {
        mPresenter.getData(ApiConfig.STORE_CLASSIFY, LoadConfig.NORMAL);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.STORE_CLASSIFY:
                StoreClassListbean storeClassListbean = (StoreClassListbean) t[0];
                if (null != storeClassListbean) {
                    List<StoreClassListbean.ResultBean> result = storeClassListbean.getResult();
                    for (int i = 0; i < result.size(); i++) {
                        mXTabLayout.addTab(mXTabLayout.newTab().setText(result.get(i).getStoreclass_name()));
                        mFragments.add(new RecommendFragment(result.get(i).getStoreclass_id() + ""));
                    }

                    mVp.setAdapter(mMyFragmentAdapter);
                    mXTabLayout.addOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
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
                    mVp.addOnPageChangeListener(new XTabLayout.TabLayoutOnPageChangeListener(mXTabLayout));
                }
                break;
        }
    }
}
