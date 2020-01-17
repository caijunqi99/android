package com.example.green.ui.activity.mine.wallet;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.green.R;
import com.example.green.base.BaseMvpActivity;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.mine.wallet.GraphListbean;
import com.example.green.config.ApiConfig;
import com.example.green.customs.GraphTools;
import com.example.green.local_utils.SPUtils;
import com.example.green.model.MineModel;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import lecho.lib.hellocharts.view.LineChartView;

public class GraphActivity extends BaseMvpActivity<CommonPresenter, MineModel> implements ICommonView {

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.chart)
    LineChartView mChart;
    private String key;
    private static final String TAG = "GraphActivity";

    @Override
    protected void initView() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        key = SPUtils.getInstance().getValue(SPUtils.KEY_USER_TOKEN, "");
    }

    @Override
    protected void initData() {
        mPresenter.getData(ApiConfig.GRAPH, key);
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
        return R.layout.activity_graph;
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.GRAPH:
                GraphListbean graphListbean = (GraphListbean) t[0];
                if (null != graphListbean && graphListbean.getCode().equals("200")) {
                    List<GraphListbean.ResultBean.ListBean>
                            list = graphListbean.getResult().getList();
                    GraphTools.setChartViewData(list, mChart);
                } else {
                    toastActivity(graphListbean.getMessage());
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
