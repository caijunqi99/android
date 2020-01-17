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
import com.example.green.adapter.mine.wallet.StoredValueItemAdapter;
import com.example.green.base.BaseMvpActivity;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.mine.QueryPropertybean;
import com.example.green.bean.mine.wallet.StoredValueListbean;
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

public class StoredActivity extends BaseMvpActivity<CommonPresenter, MineModel>
        implements ICommonView {

    @BindView(R.id.login_back)
    ImageView mLoginBack;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.jiang)
    TextView mJiang;
    @BindView(R.id.ll_chongzhi)
    LinearLayout mLlChongzhi;
    @BindView(R.id.SmartRefresh)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.jiang_recycler)
    RecyclerView mJiangRecycler;
    private String key;
    private int page;
    private int pageSize = 10;
    private List<StoredValueListbean.ResultBean.ListBean> mListBeans;
    private StoredValueItemAdapter mStoredValueItemAdapter;
    private StoredValueListbean mStoredValueListbean;

    @Override
    protected void initView() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        key = SPUtils.getInstance().getValue(SPUtils.KEY_USER_TOKEN, "");
        mListBeans = new ArrayList<>();
        mStoredValueItemAdapter = new StoredValueItemAdapter(R.layout.record_item, mListBeans);
        mJiangRecycler.setLayoutManager(new LinearLayoutManager(this));
        mJiangRecycler.setAdapter(mStoredValueItemAdapter);

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                mPresenter.getData(ApiConfig.CHUZHI_DETAIL, key, page, pageSize, LoadConfig.REFRESH);
            }
        });
        mSmartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (mStoredValueListbean.isHasmore() == true) {
                    mPresenter.getData(ApiConfig.CHUZHI_DETAIL, key, ++page, pageSize, LoadConfig.LOADMORE);
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
        mPresenter.getData(ApiConfig.CHUZHI_DETAIL, key, page, pageSize, LoadConfig.NORMAL);
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
        return R.layout.activity_stored;
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
                    String predepoit = queryPropertybean.getResult().getPredepoit();
                    mJiang.setText(predepoit + "(元)");
                } else if (queryPropertybean.getCode().equals("10086")) {
                    showInfoDialog(queryPropertybean.getMessage());
                }
                break;
            case ApiConfig.CHUZHI_DETAIL:
                mStoredValueListbean = (StoredValueListbean) t[0];
                if (null != mStoredValueListbean && mStoredValueListbean.getCode().equals("200")) {
                    List<StoredValueListbean.ResultBean.ListBean> list = mStoredValueListbean.getResult().getList();
                    int loadMode = (int) t[1];
                    if (loadMode == LoadConfig.NORMAL) {
                        mListBeans.addAll(list);
                    } else if (loadMode == LoadConfig.REFRESH) {
                        mListBeans.clear();
                        mListBeans.addAll(list);
                        mSmartRefreshLayout.finishRefresh();
                    } else if (loadMode == LoadConfig.LOADMORE) {
                        mListBeans.addAll(list);
                        mSmartRefreshLayout.finishLoadmore();
                    }
                    mStoredValueItemAdapter.notifyDataSetChanged();
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

    @OnClick({R.id.login_back, R.id.ll_chongzhi})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.login_back:
                finish();
                break;
            case R.id.ll_chongzhi: // 充值
                //设置切换动画，从右边进入，左边退出
                overridePendingTransition(R.anim.in_from_right,
                        R.anim.out_to_left);
                startActivity(new Intent(StoredActivity.this, RechargeActivity.class)); // 去充值 充值成功后 刷新数据
                break;
        }
    }
}
