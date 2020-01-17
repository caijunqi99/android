package com.example.green.base;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver implements Observer {
    private Disposable d;

    public abstract void onNetError(Throwable e);

    protected abstract void onNetSuccess(Object value);

    @Override
    public void onSubscribe(Disposable d) {
        this.d = d;
    }

    @Override
    public void onNext(Object value) {
        onNetSuccess(value);
        dispose();
    }

    @Override
    public void onError(Throwable e) {
        onNetError(e);
        dispose();
    }

    @Override
    public void onComplete() {

    }

    private void dispose() {
        if (d != null && !d.isDisposed()) {
            d.dispose();
        }
    }
}
