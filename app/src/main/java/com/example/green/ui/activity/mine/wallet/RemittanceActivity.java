package com.example.green.ui.activity.mine.wallet;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.green.R;
import com.example.green.base.BaseMvpActivity;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.pay.RemittanceInfobean;
import com.example.green.config.ApiConfig;
import com.example.green.local_utils.SPUtils;
import com.example.green.model.MineModel;

import butterknife.BindView;
import butterknife.OnClick;

public class RemittanceActivity extends BaseMvpActivity<CommonPresenter, MineModel> implements ICommonView {

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_kaihuming)
    TextView mTvKaihuming;
    @BindView(R.id.tv_bank)
    TextView mTvBank;
    @BindView(R.id.tv_card_num)
    TextView mTvCardNum;
    @BindView(R.id.remark)
    TextView mRemark;


    @Override
    protected void initView() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
    }

    @Override
    protected void initData() {
        mPresenter.getData(ApiConfig.REMITTANCE, SPUtils.getInstance().getValue(SPUtils.KEY_USER_TOKEN, ""));
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
        return R.layout.activity_remittance;
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.REMITTANCE:
                RemittanceInfobean remittanceInfobean = (RemittanceInfobean) t[0];
                if (null != remittanceInfobean && remittanceInfobean.getCode().equals("200")) {
                    mTvKaihuming.setText(remittanceInfobean.getResult().getName());
                    mTvBank.setText(remittanceInfobean.getResult().getBank());
                    mTvCardNum.setText(remittanceInfobean.getResult().getAccount());
                    mRemark.setText(remittanceInfobean.getResult().getCode());
                } else {
                    toastActivity(remittanceInfobean.getMessage());
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
