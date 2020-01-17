package com.example.green.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseMvpFragment<T extends BasePresenter, M> extends BaseFragment implements ICommonView {
    protected T mPresenter;
    public M mModel;
    private Unbinder bind;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(getContext()).inflate(getLayoutId(), null);
        bind = ButterKnife.bind(this, view);
        mModel = initModel();
        mPresenter = initPresenter();
        mPresenter.attach(this, (ICommonModel) mModel);
        initView();
        initListener();
        initData();
        return view;
    }

    public void netErrorToast(Throwable e) {
        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    protected abstract T initPresenter();

    protected abstract M initModel();

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    protected void initListener() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
        mPresenter.detach();
    }
}
