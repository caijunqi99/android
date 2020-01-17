package com.example.green.ui.activity.mine.wallet;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.green.R;
import com.example.green.adapter.mine.wallet.PointItemAdapter;
import com.example.green.base.BaseMvpActivity;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.mine.QueryPropertybean;
import com.example.green.bean.mine.wallet.PointListbean;
import com.example.green.config.ApiConfig;
import com.example.green.config.LoadConfig;
import com.example.green.customs.RoundCornerDialog;
import com.example.green.local_utils.SPUtils;
import com.example.green.model.MineModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class IntegralActivity extends BaseMvpActivity<CommonPresenter, MineModel>
        implements ICommonView {


    @BindView(R.id.login_back)
    ImageView mLoginBack;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.ji_point)
    TextView mJiPoint;
    @BindView(R.id.ji_available)
    TextView mJiAvailable;
    @BindView(R.id.rl_huzhuang)
    LinearLayout mLlHuzhuan;
    @BindView(R.id.rl_tixian)
    LinearLayout mLlTixian;
    @BindView(R.id.SmartRefresh)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.jifen_recyclerview)
    RecyclerView mJifenRecyclerview;
    private String key;
    private int page;
    private int pageSize = 10;
    private List<PointListbean.ResultBean.LogListBean> mLogListBeans;
    private PointItemAdapter mPointItemAdapter;
    private PointListbean mPointListbean;

    @Override
    protected void initView() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        key = SPUtils.getInstance().getValue(SPUtils.KEY_USER_TOKEN, "");
        mLogListBeans = new ArrayList<>();
        mPointItemAdapter = new PointItemAdapter(R.layout.record_item, mLogListBeans);
        mJifenRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mJifenRecyclerview.setAdapter(mPointItemAdapter);
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                mPresenter.getData(ApiConfig.JIFEN_DETAIL, key, page, pageSize, LoadConfig.REFRESH);
            }
        });
        mSmartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (mPointListbean.isHasmore() == true) {
                    mPresenter.getData(ApiConfig.JIFEN_DETAIL, key, ++page, pageSize, LoadConfig.LOADMORE);
                } else {
                    mSmartRefreshLayout.finishLoadmore();
                }
            }
        });

    }

    @Override
    protected void initData() {
        page = 1;
        mPresenter.getData(ApiConfig.QUERY_PROPERT, key);
        mPresenter.getData(ApiConfig.JIFEN_DETAIL, key, page, pageSize, LoadConfig.NORMAL);
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
        return R.layout.activity_integral;
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.QUERY_PROPERT:
                QueryPropertybean queryPropertybean = (QueryPropertybean) t[0];
                if (null != queryPropertybean && queryPropertybean.getCode().equals("200")) {
                    String available = queryPropertybean.getResult().getAvailable();
                    String point = queryPropertybean.getResult().getPoint();
                    mJiAvailable.setText(available);
                    mJiPoint.setText(point);
                } else if (queryPropertybean.getCode().equals("10086")) {
                    showInfoDialog(queryPropertybean.getMessage());
                }
                break;
            case ApiConfig.JIFEN_DETAIL:
                mPointListbean = (PointListbean) t[0];
                if (null != mPointListbean && mPointListbean.getCode().equals("200")) {
                    List<PointListbean.ResultBean.LogListBean> log_list = mPointListbean.getResult().getLog_list();
                    int loadMode = (int) t[1];
                    if (loadMode == LoadConfig.NORMAL) {
                        mLogListBeans.addAll(log_list);
                    } else if (loadMode == LoadConfig.REFRESH) {
                        mLogListBeans.clear();
                        mLogListBeans.addAll(log_list);
                        mSmartRefreshLayout.finishRefresh();
                    } else if (loadMode == LoadConfig.LOADMORE) {
                        mLogListBeans.addAll(log_list);
                        mSmartRefreshLayout.finishLoadmore();
                    }
                    mPointItemAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    private void showInfoDialog(String msg) {
        View view = View.inflate(this, R.layout.dialog_withdraw_confirm, null);
        final RoundCornerDialog roundCornerDialog = new RoundCornerDialog(this, 0, 0, view, R.style.RoundCornerDialog);
        roundCornerDialog.show();
        roundCornerDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        roundCornerDialog.setOnKeyListener(keylistener);//设置点击返回键Dialog不消失

        TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
        TextView tv_logout_confirm = (TextView) view.findViewById(R.id.tv_logout_confirm);
        tv_message.setText(msg);
        // 确定
        tv_logout_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                roundCornerDialog.dismiss();
                finish();
            }
        });
    }

    DialogInterface.OnKeyListener keylistener = new DialogInterface.OnKeyListener() {
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                return true;
            } else {
                return false;
            }
        }
    };

    @OnClick({R.id.login_back, R.id.rl_huzhuang, R.id.rl_tixian})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.login_back:
                finish();
                break;
            case R.id.rl_huzhuang:
                //设置切换动画，从右边进入，左边退出
                overridePendingTransition(R.anim.in_from_right,
                        R.anim.out_to_left);
                startActivity(new Intent(IntegralActivity.this, TransfersActivity.class));
                break;
            case R.id.rl_tixian:
                //设置切换动画，从右边进入，左边退出
                overridePendingTransition(R.anim.in_from_right,
                        R.anim.out_to_left);
                startActivity(new Intent(IntegralActivity.this, WithdrawActivity.class));
                break;
        }
    }
}
