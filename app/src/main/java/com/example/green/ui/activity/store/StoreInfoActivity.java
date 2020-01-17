package com.example.green.ui.activity.store;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.green.R;
import com.example.green.base.BaseMvpActivity;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.store.StoreInfoListbean;
import com.example.green.config.ApiConfig;
import com.example.green.config.LoadConfig;
import com.example.green.model.StoreModel;
import com.example.green.ui.fragment.store.AllGoodsFragment;
import com.example.green.ui.fragment.store.StoreHomePageFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class StoreInfoActivity extends BaseMvpActivity<CommonPresenter, StoreModel>
        implements ICommonView {

    private static final String STORE_ID = "storeId";// 商品ID
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.store_name)
    TextView storeName;
    @BindView(R.id.store_bg)
    ImageView storeBg;
    @BindView(R.id.store_logo)
    ImageView storeLogo;
    @BindView(R.id.goods_name)
    TextView goodsName;
    @BindView(R.id.rl_homepage)
    RelativeLayout mRlHomepage;
    @BindView(R.id.rl_allgoods)
    RelativeLayout mRlAllgoods;
    @BindView(R.id.homepage)
    TextView homePage;
    @BindView(R.id.allgoods)
    TextView allGoods;
    @BindView(R.id.tip_1)
    ImageView mTip1;
    @BindView(R.id.tip_2)
    ImageView mTip2;
    @BindView(R.id.fl)
    FrameLayout mFl;

    public static final int FRAGMENT_ONE = 0;
    public static final int FRAGMENT_TWO = 1;
    public FragmentManager fragmentManager;
    private StoreHomePageFragment mStoreHomePageFragment;
    private AllGoodsFragment mAllGoodsFragment;
    private String mStoreId;

    /**
     * 开启页面 传值
     *
     * @param mActivity
     * @param mStoreId
     */
    public static void startInfoActivity(Activity mActivity, String mStoreId) {
        Intent intent = new Intent(mActivity, StoreInfoActivity.class);
        intent.putExtra(STORE_ID, mStoreId);
        mActivity.startActivity(intent);
    }

    @Override
    protected void initView() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        fragmentManager = getSupportFragmentManager();
        // 拿到传回的storeId
        Intent intent = getIntent();
        mStoreId = intent.getStringExtra("storeId");

        // 店铺首页
        selectFragment(FRAGMENT_ONE);
        mTip1.setVisibility(View.VISIBLE);
        mTip2.setVisibility(View.GONE);
        homePage.setTextColor(getResources().getColor(R.color.c_27b28b));
    }

    @Override
    protected void initData() {
        mPresenter.getData(ApiConfig.STORE_INFO, mStoreId, LoadConfig.NORMAL);
    }

    @Override
    protected StoreModel initModel() {
        return new StoreModel();
    }

    @Override
    protected CommonPresenter initPresenter() {
        return new CommonPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_store_info;
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.STORE_INFO:
                StoreInfoListbean storeInfoListbean = (StoreInfoListbean) t[0];
                if (null != storeInfoListbean) {
                    StoreInfoListbean.ResultBean.StoreInfoBean
                            store_info = storeInfoListbean.getResult().getStore_info();
                    storeName.setText(store_info.getStore_name());
                    goodsName.setText(store_info.getStore_name());
                    Glide.with(this).load(store_info.getMb_title_img()).into(storeBg);
                    Glide.with(this).load(store_info.getStore_avatar()).into(storeLogo);

                }
                break;
        }
    }


    @OnClick({R.id.back, R.id.rl_homepage, R.id.rl_allgoods})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                finish();
                break;
            case R.id.rl_homepage:
                // 店铺首页
                selectFragment(FRAGMENT_ONE);
                mTip1.setVisibility(View.VISIBLE);
                mTip2.setVisibility(View.GONE);
                homePage.setTextColor(getResources().getColor(R.color.c_27b28b));
                allGoods.setTextColor(getResources().getColor(R.color.black));
                break;
            case R.id.rl_allgoods:
                // 全部商品
                selectFragment(FRAGMENT_TWO);
                mTip1.setVisibility(View.GONE);
                mTip2.setVisibility(View.VISIBLE);
                homePage.setTextColor(getResources().getColor(R.color.black));
                allGoods.setTextColor(getResources().getColor(R.color.c_27b28b));
                break;
        }
    }

    public void selectFragment(int position) {//设置传入第几值显示第几个fragment
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        switch (position) {
            case 0:
                if (mStoreHomePageFragment == null) {
                    mStoreHomePageFragment = new StoreHomePageFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("storeId", mStoreId);
                    mStoreHomePageFragment.setArguments(bundle);
                }
                //将原来的Fragment替换掉---此处R.id.fragmen指的是FrameLayout
                ft.replace(R.id.fl, mStoreHomePageFragment);
                break;
            case 1:
                if (mAllGoodsFragment == null) {
                    mAllGoodsFragment =new AllGoodsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("storeId", mStoreId);
                    mAllGoodsFragment.setArguments(bundle);
                }
                ft.replace(R.id.fl, mAllGoodsFragment);
                break;
            default:
                break;
        }
        ft.commit();
    }
}
