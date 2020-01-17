package com.example.green.base;

public interface ICommonModel<T> {
    void getData(ICommonView view, int whichApi, T... t);
}

