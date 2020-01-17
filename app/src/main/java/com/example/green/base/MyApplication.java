package com.example.green.base;


import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.util.Log;


import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.mmkv.MMKV;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import cn.jpush.android.api.JPushInterface;

public class MyApplication extends Application {
    public static final String APP_ID = "wxb53dbb4718418f8e";
    private static MyApplication mApplication;
    public static String mToken;

    private static String otherUserId;//其他用户的ID

    public static boolean isIsScreening() {
        return isScreening;
    }

    public static void setIsScreening(boolean isScreening) {
        MyApplication.isScreening = isScreening;
    }

    public static boolean isScreening;


    public static String getmToken() {
        return mToken;
    }

    public static void setmToken(String pMToken) {
        mToken = pMToken;
    }

    private RefWatcher mRefWatcher;
    public static String uuid;

    public static void setUuid(String pUuid) {
        uuid = pUuid;
    }

/*    public static String getUuid() {
        String userId = SPUtils.getInstance().getValue(SPUtils.KEY_USER_ID, "");
        return userId;
    }*/

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;

        MMKV.initialize(this);
        if (Build.VERSION.SDK_INT >= 28) {
            closeAndroidPDialog();
        }

        /*UMShareAPI.get(mApplication);//初始化sdk
         *//*初始化友盟接口*//*
        UMConfigure.init(mApplication, "5d2eb90a570df3c1ac000a62", "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
        //各个平台的配置
        {
            // 微信
            PlatformConfig.setWeixin("wx983729df34d4b9be", "8b07c611e8639f8bb34dd764f23042e4");
            // 新浪微博(第三个参数为回调地址)
            PlatformConfig.setSinaWeibo("1428615272", "36a3eb5d52ff291c523da82652844e56", "https://api.weibo.com/oauth2/default.html");//第三个参数为授权回调页的地址
            // QQ
            PlatformConfig.setQQZone("101728228", "b2e9a0e0cbc574ec05decb3539a07038");
        }*/

        // 初始化极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(mApplication);

    }

    private RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }


    public RefWatcher getRefWatcher() {
        return mRefWatcher;
    }

    public static MyApplication getApplication() {
        return mApplication != null ? mApplication : null;
    }


    public static Context getAppContext() {
        return mApplication.getApplicationContext();
    }

    // 干掉非法弹窗
    private void closeAndroidPDialog() {
        try {
            Class aClass = Class.forName("android.content.pm.PackageParser$Package");
            Constructor declaredConstructor = aClass.getDeclaredConstructor(String.class);
            declaredConstructor.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread");
            declaredMethod.setAccessible(true);
            Object activityThread = declaredMethod.invoke(null);
            Field mHiddenApiWarningShown = cls.getDeclaredField("mHiddenApiWarningShown");
            mHiddenApiWarningShown.setAccessible(true);
            mHiddenApiWarningShown.setBoolean(activityThread, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
