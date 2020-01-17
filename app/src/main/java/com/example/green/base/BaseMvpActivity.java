package com.example.green.base;

import android.os.Bundle;

import com.yiyatech.utils.ext.ToastUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseMvpActivity<T extends BasePresenter, M> extends BaseActivity implements ICommonView {
    protected T mPresenter;
    public M mModel;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        bind = ButterKnife.bind(this);
        mModel = initModel();
        mPresenter = initPresenter();
        mPresenter.attach(this, (ICommonModel) mModel);

        initView();
        initListener();
        initData();
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract M initModel();

    protected abstract T initPresenter();

    protected abstract int getLayoutId();

    protected void initListener() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
        mPresenter.detach();
    }

    protected void toastActivity(String msg) {
        ToastUtils.show(this, msg);
    }
}
