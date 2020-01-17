package com.example.green.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.green.R;
import com.example.green.adapter.classify.ThemeMainAdapter;
import com.example.green.base.BaseMvpFragment;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.classify.AllClassifyListbean;
import com.example.green.config.ApiConfig;
import com.example.green.config.LoadConfig;
import com.example.green.model.ClassifyModel;
import com.example.green.ui.activity.SearchActivity;
import com.example.green.ui.fragment.classify.RightClassifyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassifyFragment extends BaseMvpFragment<CommonPresenter, ClassifyModel>
        implements ICommonView, ThemeMainAdapter.OnSelectorListener {

    @BindView(R.id.ll_search)
    RelativeLayout mLlSearch;
    @BindView(R.id.allclassify_recyclerview)
    RecyclerView recyclerView;

    private ThemeMainAdapter mThemeMainAdapter;
    private List<AllClassifyListbean.ResultBean.ClassListBean> mClassListBeans;
    private RightClassifyFragment mRightClassifyFragment;

    static ClassifyFragment fragment;

    public static ClassifyFragment newInstance() {
        if (fragment == null) fragment = new ClassifyFragment();
        return fragment;
    }

    public ClassifyFragment() {
        // Required empty public constructor
    }

    @Override
    protected CommonPresenter initPresenter() {
        return new CommonPresenter();
    }

    @Override
    protected ClassifyModel initModel() {
        return new ClassifyModel();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_classify;
    }

    @Override
    protected void initView() {
        mThemeMainAdapter = new ThemeMainAdapter(getContext());
        mClassListBeans = new ArrayList<>();

        //初始化recyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mThemeMainAdapter.setOnSelectorListener(this);
    }

    @Override
    protected void initData() {
        mPresenter.getData(ApiConfig.URL_ALLCLASSIFY, LoadConfig.NORMAL);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.URL_ALLCLASSIFY:
                AllClassifyListbean allClassifyListbean
                        = (AllClassifyListbean) t[0];
                if (null != allClassifyListbean) {
                    mClassListBeans.addAll(allClassifyListbean.getResult().getClass_list());
                    //数据处理
                    dealWithData(mClassListBeans);
                }
                break;
        }
    }

    @OnClick(R.id.ll_search)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.ll_search:
                startActivity(new Intent(getContext(), SearchActivity.class));
                break;
        }
    }

    private void dealWithData(List<AllClassifyListbean.ResultBean.ClassListBean> datasBeanList) {
        //传递数据
        mThemeMainAdapter.setData(datasBeanList);
        recyclerView.setAdapter(mThemeMainAdapter);
        //默认选中
        datasBeanList.get(0).setChick(true);
        //创建Fragment对象
        mRightClassifyFragment = new RightClassifyFragment();
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, mRightClassifyFragment);

        //传递数据到Fragment
        Bundle mBundle = new Bundle();
        mBundle.putSerializable("gc_id", mClassListBeans.get(0));
        mRightClassifyFragment.setArguments(mBundle);
        fragmentTransaction.commit();
    }

    public void onSelect(View view, int position) {

        //选中处理
        AllClassifyListbean.ResultBean.ClassListBean classListBean = mClassListBeans.get(position);
        for (int i = 0; i < mClassListBeans.size(); i++) {
            if (mClassListBeans.get(i).getGc_name().equals(classListBean.getGc_name())) {
                mClassListBeans.get(i).setChick(true);
            } else {
                mClassListBeans.get(i).setChick(false);
            }
        }

        //刷新列表
        mThemeMainAdapter.notifyDataSetChanged();

        //创建Fragment对象
        mRightClassifyFragment = new RightClassifyFragment();
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, mRightClassifyFragment);

        //传递数据到Fragment
        Bundle mBundle = new Bundle();
        mBundle.putSerializable("gc_id", mClassListBeans.get(position));
        mRightClassifyFragment.setArguments(mBundle);
        fragmentTransaction.commit();
    }

}
