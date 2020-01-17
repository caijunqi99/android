package com.example.green.base;

public interface ICommonPresenter<T> {
    void getData(int whichApi, T... t);
}
