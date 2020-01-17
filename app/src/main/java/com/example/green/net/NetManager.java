package com.example.green.net;


import com.example.green.base.BaseObserver;
import com.example.green.base.ICommonView;
import com.example.green.base.NetInterceptor;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetManager {
    private static volatile NetManager sNetManager;

    private NetManager() {
    }

    public static NetManager getNetManager() {
        if (sNetManager == null) {//考虑效率问题
            synchronized (NetManager.class) {
                if (sNetManager == null) {//考虑多个线程问题
                    sNetManager = new NetManager();
                }
            }
        }
        return sNetManager;
    }

    public INetService getHttpsUrlService() { // 域名为HTTPS的接口数据有实体类
        INetService service = new Retrofit.Builder()
                .baseUrl(NetConfig.HTTPS_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                .build().create(INetService.class);
        return service;
    }

    public INetService getHttpSportUrlService() { // 域名为HTTPS的接口数据有实体类
        INetService service = new Retrofit.Builder()
                .baseUrl(NetConfig.HTTP_SPORTURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(INetService.class);
        return service;
    }

    public INetService getHttpsSportService() {
        INetService service = new Retrofit.Builder()
                .baseUrl(NetConfig.HTTPS_SPORTURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(INetService.class);
        return service;
    }

/*    public INetService getHttpsApiService() { // 域名为api的接口数据有实体类
        INetService service = new Retrofit.Builder()
                .baseUrl(NetConfig.HTTPS_API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(INetService.class);
        return service;
    }*/

    public INetService getHttpService() {
        INetService service = new Retrofit.Builder()
                .baseUrl(NetConfig.Url_TEST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(NetInterceptor.getNetInterceptor().getClientWithoutCache())
                .build().create(INetService.class);
        return service;
    }

    public INetService getHttpShoppingService() {
        INetService service = new Retrofit.Builder()
                .baseUrl(NetConfig.Url_SHOPPING)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(NetInterceptor.getNetInterceptor().getClientWithoutCache())
                .build().create(INetService.class);
        return service;
    }

    /**
     * 上传图片使用
     *
     * @return
     */
    public INetService getHttpUploadService() {
        INetService service = new Retrofit.Builder()
                .baseUrl(NetConfig.Url_Upload)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(NetInterceptor.getNetInterceptor().getClientWithoutCache())
                .build().create(INetService.class);
        return service;
    }

    /*public INetService getINSService() {
        INetService service = new Retrofit.Builder()
                .baseUrl(NetConfig.INSURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(INetService.class);
        return service;
    }*/

    // 返回无泛型(ResponseBody)的观察者
    public <T> void netResponsebody(Observable<ResponseBody> observable, final ICommonView view, final int whichApi, final int p) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody value) {
                        try {
                            view.onResponse(whichApi, value, p);
                        } catch (Exception pE) {
                            pE.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    // 返回有泛型的观察者
    public <T> void netMethod(Observable<T> observable, final ICommonView view, final int whichApi, final int p) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver() {
                    @Override
                    public void onNetError(Throwable e) {
                        view.onError(e);
                    }

                    @Override
                    protected void onNetSuccess(Object value) {
                        view.onResponse(whichApi, value, p);
                    }
                });
    }

    /*// 返回有泛型的观察者
    public <T> void netMethod(Observable<T> observable, final ICommonView view, final int whichApi, final int p) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<T>() {
                    @Override
                    public void accept(T pT) throws Exception {
                        view.onResponse(whichApi, pT);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable pThrowable) throws Exception {
                        view.onError(pThrowable);
                    }
                });
    }*/
    // 返回有泛型的观察者 无页码参数
    public <T> void NetMethod(Observable<T> mObservable, final ICommonView mView, final int mWhichApi) {
        mObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver() {
                    @Override
                    public void onNetError(Throwable e) {
                        mView.onError(e);
                    }

                    @Override
                    protected void onNetSuccess(Object value) {
                        mView.onResponse(mWhichApi, value); // 不含页码
                    }
                });
    }
}
