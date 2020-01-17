package com.example.green.base;

import android.util.Log;


public class CommonPresenter extends BasePresenter implements ICommonPresenter, ICommonView {

    @Override
    public void getData(int whichApi, Object... t) {
        if (getModel() != null) {
            getModel().getData(this, whichApi, t);
        } else {
            Log.e("net error", "found error when excute netWork to used getModel():" + whichApi);
        }

    }

    @Override
    public void onError(Throwable e) {
        getView().onError(e);
    }

    @Override
    public void onResponse(int whichApi, Object[] o) {
        if (getView() != null) {
            getView().onResponse(whichApi, o);
        } else {
            Log.e("onResponse error", "getView() found error when onResponse  :" + whichApi);
        }
    }
}
