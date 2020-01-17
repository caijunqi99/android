package com.example.green.ui.activity.mine.wallet;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.green.R;
import com.example.green.base.BaseMvpActivity;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.model.MineModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TopUpActivity extends BaseMvpActivity<CommonPresenter, MineModel>
        implements ICommonView {

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_huming)
    EditText mEtHuming;
    @BindView(R.id.et_bank)
    EditText mEtBank;
    @BindView(R.id.et_card)
    EditText mEtCard;
    @BindView(R.id.et_remark)
    EditText mEtRemark;
    @BindView(R.id.bt_top_up)
    Button mBtTopUp;

    @Override
    protected void initView() {

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
        return R.layout.activity_top_up;
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }

    @OnClick({R.id.back, R.id.bt_top_up})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                finish();
                break;
            case R.id.bt_top_up:
                break;
        }
    }
}
