package com.example.green.ui.fragment.mine;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.green.R;
import com.example.green.adapter.mine.MyCollegeAdapter;
import com.example.green.base.BaseMvpFragment;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.mine.CollegeListbean;
import com.example.green.config.ApiConfig;
import com.example.green.config.LoadConfig;
import com.example.green.model.MineModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yiyatech.utils.ext.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends BaseMvpFragment<CommonPresenter, MineModel>
        implements ICommonView {
    @BindView(R.id.video_recyclerview)
    RecyclerView mVideoRecyclerview;
    @BindView(R.id.SmartRefresh)
    SmartRefreshLayout mRefreshLayout;
    private List<CollegeListbean.ResultBean> mResultBeans;
    private MyCollegeAdapter mMyCollegeAdapter;
    private int page = 1;

    static VideoFragment fragment;

    public VideoFragment() {
        // Required empty public constructor
    }

    public static VideoFragment newInstance() {
        if (fragment == null) {
            fragment = new VideoFragment();
        }
        return fragment;
    }


    @Override
    protected CommonPresenter initPresenter() {
        return new CommonPresenter();
    }

    @Override
    protected MineModel initModel() {
        return new MineModel();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initView() {
        mResultBeans = new ArrayList<>();
        mMyCollegeAdapter = new MyCollegeAdapter(R.layout.layout_college_item, mResultBeans);
        mVideoRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mVideoRecyclerview.setAdapter(mMyCollegeAdapter);

        mMyCollegeAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.rl_college_card:
                        ToastUtils.show(getContext(), "跳转");
                        break;
                }
            }
        });
        mRefreshLayout.setEnableRefresh(false);
    }

    @Override
    protected void initData() {
        mPresenter.getData(ApiConfig.COLLEGE, 1, page, LoadConfig.NORMAL);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.COLLEGE:
                CollegeListbean collegeListbean = (CollegeListbean) t[0];
                if (null != collegeListbean) {
                    List<CollegeListbean.ResultBean> result = collegeListbean.getResult();
                    mResultBeans.addAll(result);
                    mMyCollegeAdapter.notifyDataSetChanged();
                }
                break;
        }
    }
}
