package com.example.green.down;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface DownApi {
    /**
     * 下载的使用
     *
     * @return
     */
    @GET
    Call<ResponseBody> downApk(@Url String address);


}

