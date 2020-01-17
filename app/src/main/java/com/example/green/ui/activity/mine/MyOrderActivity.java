package com.example.green.ui.activity.mine;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.androidkun.xtablayout.XTabLayout;
import com.example.green.R;
import com.example.green.adapter.store.MyFragmentAdapter;
import com.example.green.base.BaseMvpActivity;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.model.MineModel;
import com.example.green.ui.fragment.mine.GoodsOrderFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MyOrderActivity extends BaseMvpActivity<CommonPresenter, MineModel>
        implements ICommonView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tab)
    XTabLayout mTab;
    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.back)
    ImageView mBack;
    private MyFragmentAdapter mMyFragmentAdapter;
    private List<Fragment> mFragments;
    private int Index;
    private static final String TAG = "MyOrderActivity";

    @Override
    protected void initView() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        Intent intent = getIntent();
        Index = intent.getIntExtra("index", -1);

        mFragments = new ArrayList<>();
        mFragments.add(GoodsOrderFragment.newInstance("")); // 全部
        mFragments.add(GoodsOrderFragment.newInstance("state_new")); // 待付款
        mFragments.add(GoodsOrderFragment.newInstance("state_pay")); // 待发货
        mFragments.add(GoodsOrderFragment.newInstance("state_send")); // 待收货
        mFragments.add(GoodsOrderFragment.newInstance("state_success")); // 已完成
        mFragments.add(GoodsOrderFragment.newInstance("state_cancel")); // 已取消
        mMyFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(), mFragments);
        mTab.addTab(mTab.newTab().setText("全部"));
        mTab.addTab(mTab.newTab().setText("待付款"));
        mTab.addTab(mTab.newTab().setText("待发货"));
        mTab.addTab(mTab.newTab().setText("待收货"));
        mTab.addTab(mTab.newTab().setText("已完成"));
        mTab.addTab(mTab.newTab().setText("已取消"));
        mVp.setAdapter(mMyFragmentAdapter);

        mVp.setCurrentItem(Index);
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
                mVp.setCurrentItem(tab.getPosition());
            }
        });
        mVp.addOnPageChangeListener(new XTabLayout.TabLayoutOnPageChangeListener(mTab));
    }

    @Override
    protected void initData() {

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
        return R.layout.activity_my_order;
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

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
