package com.example.green.base;

public interface ICommonView<T> {
    void onError(Throwable e);

    void onResponse(int whichApi, T... t);
}
