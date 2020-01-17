package com.example.green.ui.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.example.green.R;
import com.example.green.base.BaseMvpActivity;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.homepage.Versionbean;
import com.example.green.config.ApiConfig;
import com.example.green.customs.MyBottomView;
import com.example.green.down.DownApi;
import com.example.green.down.DownUtils;
import com.example.green.down.ProcessResponseBody;
import com.example.green.down.ProgressListener;
import com.example.green.local_utils.UpdateBaseDialog;
import com.example.green.model.HomePageModel;
import com.example.green.ui.fragment.ClassifyFragment;
import com.example.green.ui.fragment.HomeFragment;
import com.example.green.ui.fragment.MineFragment;
import com.example.green.ui.fragment.ShopFragment;
import com.example.green.ui.fragment.StoreFragment;
import com.yiyatech.utils.LogUtil;
import com.yiyatech.utils.ext.AppTools;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.ycbjie.ycstatusbarlib.StatusBarUtils;
import cn.ycbjie.ycstatusbarlib.bar.YCAppBar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class MainActivity extends BaseMvpActivity<CommonPresenter, HomePageModel>
        implements ICommonView, MyBottomView.OnBottomClick {
    private static final String TAG = "MainActivity";

    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    private MyBottomView mBottomView;
    private final int HOME = 1;
    private final int STORE = 2;
    private final int CLASSIFY = 3;
    private final int SHOP = 4;
    private final int MINE = 5;
    private FragmentManager mManager;

    String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    List<String> mPermissionList = new ArrayList<>();

    // private ImageView welcomeImg = null;
    private static final int PERMISSION_REQUEST = 1;
    // 检查权限

    private String TYPE = "1";

    @Override
    protected void initView() {
        checkPermission();
        mBottomView = findViewById(R.id.bottom_view);
        mBottomView.setBottomBg(Color.WHITE);
        mBottomView.setBottomTextSize(this, 10f);
        mBottomView.setOnBottomClickListener(this);
        mManager = getSupportFragmentManager();
        showFragment(HOME);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        // TODO Auto-generated method stub
        super.onNewIntent(intent);
        int id = intent.getIntExtra("id", 0);
        if (id == 4) {
            mBottomView.setFourthSelected(); // 设置 购物车Fragment
        }
    }

    @Override
    protected void initData() {
        mPresenter.getData(ApiConfig.ACQUIRE_VERSIONCODE, String.valueOf(AppTools.getVersionName(MainActivity.this)), TYPE);
    }

    @Override
    protected HomePageModel initModel() {
        return new HomePageModel();
    }

    @Override
    protected CommonPresenter initPresenter() {
        return new CommonPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.ACQUIRE_VERSIONCODE:
                Versionbean versionbean = (Versionbean) t[0];
                if (null != versionbean && versionbean.getCode().equals("200")) {
                    // 0：版本号一致，-1：需要更新，1：当前版本大于系统版本
                    int is_update = versionbean.getResult().getIs_update();
                    // 更新方式；1：建议更新；2：强制更新
                    int mode = versionbean.getResult().getMode();
                    // 下载地址
                    String url = versionbean.getResult().getUrl();
                    if (is_update == -1) {
                        if (mode == 2) { // 强制更新
                            isForceUpdate = true;
                            forceUpdate("V " + versionbean.getResult().getVersion_num(), versionbean.getResult().getContent(), url);
                        } else { // 建议更新
                            isForceUpdate = false;
                            optionalUpdate("V " + versionbean.getResult().getVersion_num(), versionbean.getResult().getContent(), url);
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void onFirstClick() {
        showFragment(HOME);
    }

    @Override
    public void onSecondClick() {
        showFragment(STORE);
    }

    @Override
    public void onThirdClick() {
        showFragment(CLASSIFY);
    }

    @Override
    public void onFourthClick() {
        showFragment(SHOP);
    }

    @Override
    public void onFifthClick() {
        showFragment(MINE);
    }

    private void showFragment(int index) {
        FragmentTransaction fragmentTransaction = mManager.beginTransaction();
        switch (index) {
            case HOME:
                YCAppBar.setStatusBarColor(MainActivity.this,
                        ContextCompat.getColor(MainActivity.this,
                                R.color.app_theme_color));
                fragmentTransaction.replace(R.id.frame_layout, HomeFragment.newInstance());
                break;
            case STORE:
                //设置状态栏为透明，相当于隐藏状态栏，也称之为沉浸式状态栏
                YCAppBar.translucentStatusBar(MainActivity.this,
                        true);
                //状态栏亮色模式，设置状态栏黑色文字、图标
                StatusBarUtils.StatusBarLightMode(MainActivity.this);
                fragmentTransaction.replace(R.id.frame_layout, StoreFragment.newInstance());
                break;
            case CLASSIFY:
                //设置状态栏为透明，相当于隐藏状态栏，也称之为沉浸式状态栏
                YCAppBar.translucentStatusBar(MainActivity.this,
                        true);
                //状态栏亮色模式，设置状态栏黑色文字、图标
                StatusBarUtils.StatusBarLightMode(MainActivity.this);

                fragmentTransaction.replace(R.id.frame_layout, ClassifyFragment.newInstance());
                break;
            case SHOP:
                //设置状态栏为透明，相当于隐藏状态栏，也称之为沉浸式状态栏
                YCAppBar.translucentStatusBar(MainActivity.this,
                        true);
                //状态栏亮色模式，设置状态栏黑色文字、图标
                StatusBarUtils.StatusBarLightMode(MainActivity.this);

                fragmentTransaction.replace(R.id.frame_layout, ShopFragment.newInstance());
                break;
            case MINE:
                YCAppBar.setStatusBarColor(MainActivity.this,
                        ContextCompat.getColor(MainActivity.this,
                                R.color.app_theme_color));
                fragmentTransaction.replace(R.id.frame_layout, MineFragment.newInstance());
                break;
        }
        fragmentTransaction.commit();
    }

    private void checkPermission() {
        mPermissionList.clear();

        //判断哪些权限未授予
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);
            }
        }
        /**
         * 判断是否为空
         */
        if (mPermissionList.isEmpty()) {//未授予的权限为空，表示都授予了

        } else {//请求权限方法
            String[] permissions = mPermissionList.toArray(new String[mPermissionList.size()]);//将List转为数组
            ActivityCompat.requestPermissions(MainActivity.this, permissions, PERMISSION_REQUEST);
        }
    }

    /**
     * 响应授权
     * 这里不管用户是否拒绝，都进入首页，不再重复申请权限
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST:

                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }


    /********************************************************升级的逻辑****************************************************************************/

    private UpdateBaseDialog mUpdateDialog;
    private int newProgress;//下载的进度
    // 下载进度
    ProgressDialog progressDialog;
    String fileName;//文件路径
    int APK_INSTALL_BACK_1 = 1;
    int APK_INSTALL_BACK_2 = 2;//安装的回调
    private boolean isForceUpdate;//是否是强制更新


    private static class MyHandler extends Handler {
        private final WeakReference<MainActivity> mActivity;

        public MyHandler(MainActivity activity) {
            mActivity = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case 100://正在下载
                        int progress = (int) msg.obj;
                        activity.setDialogProgress(progress);
                        break;

                    case 101://下载完成
                        activity.dismissDialog();
                        activity.showToast("下载完成，准备安装");
                        activity.openAPKFile();
                        break;
                    case 102://保存失败（或者下载失败）
                        activity.showToast("遇到未知错误，下载失败");
                        break;
                }


                // ...
            }
        }
    }

    private final MyHandler mHandler = new MyHandler(this);


    /**
     * 普通升级
     *
     * @param version
     * @param info
     */
    private void optionalUpdate(String version, String info, final String urlApk) {
        mUpdateDialog = new UpdateBaseDialog(this, R.layout.dialog_optional_update, version, info);
        mUpdateDialog.setOnCenterItemClickListener(new UpdateBaseDialog.OnCenterItemClickListener() {
            @Override
            public void OnCenterItemClick(UpdateBaseDialog dialog, View view) {

                switch (view.getId()) {
                    case R.id.tv_later://
                        dialog.dismiss();
                        break;
                    case R.id.tv_update://马上更新
                        dialog.dismiss();
                        fileName = Environment.getExternalStorageDirectory() + File.separator + "green.apk";
                        downLoad(urlApk);
                        break;
                }
            }
        });
        mUpdateDialog.show();
        mUpdateDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        mUpdateDialog.setOnKeyListener(keylistener);//设置点击返回键Dialog不消失
    }

    /**
     * 强制升级
     *
     * @param version
     * @param info
     * @param urlApk
     */
    private void forceUpdate(String version, String info, final String urlApk) {
        mUpdateDialog = new UpdateBaseDialog(this, R.layout.dialog_force_update, version, info);
        mUpdateDialog.setOnCenterItemClickListener(new UpdateBaseDialog.OnCenterItemClickListener() {
            @Override
            public void OnCenterItemClick(UpdateBaseDialog dialog, View view) {
                fileName = Environment.getExternalStorageDirectory() + File.separator + "green.apk";
                downLoad(urlApk);
            }
        });
        mUpdateDialog.setCanceledOnTouchOutside(false);
        mUpdateDialog.setCancelable(false);
        mUpdateDialog.show();
        mUpdateDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        mUpdateDialog.setOnKeyListener(keylistener);//设置点击返回键Dialog不消失
    }

    DialogInterface.OnKeyListener keylistener = new DialogInterface.OnKeyListener() {
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                return true;
            } else {
                return false;
            }
        }
    };

    void downLoad(String apkUrl) {
        initProgressDialog();
        newProgress = 0;
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(apkUrl + "/");
        //下载进度的监听器
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new Interceptor() {

                    @Override
                    public Response intercept(Chain chain) throws IOException {

                        Response orginalResponse = chain.proceed(chain.request());
                        return orginalResponse.newBuilder()
                                .body(new ProcessResponseBody(orginalResponse.body(), new ProgressListener() {
                                    @Override
                                    public void onProgress(long progress, long total, boolean done) {

                                        // 计算已下载文件大小的百分比
                                        BigDecimal totalSize = new BigDecimal(((total / 1024f) / 1024f)).setScale(2, BigDecimal.ROUND_HALF_UP);
                                        BigDecimal progressSize = new BigDecimal(((progress / 1024f) / 1024f)).setScale(2, BigDecimal.ROUND_HALF_UP);

                                        if (TextUtils.equals(String.valueOf(totalSize), "0.00")) {
                                            showToast("目标文件为空文件");
                                            return;
                                        }

                                        LogUtil.d("数值", "onProgress: 共" + totalSize + "MB，已下载" + progressSize + "MB");
                                        if ((int) ((progress * 100) / total) > newProgress) {
                                            newProgress = (int) ((progress * 100) / total);
                                            Message msg = mHandler.obtainMessage();
                                            msg.what = 100;
                                            msg.obj = newProgress;
                                            mHandler.sendMessage(msg);
                                            LogUtil.d("数值", "onProgress: 下载百分比-->" + newProgress);
                                        }
                                    }
                                }))
                                .build();
                    }
                })
                .build();


        //加载监听器
        DownApi download = builder.client(client).build().create(DownApi.class);
        //开始下载
        download.downApk(apkUrl).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                //下载完成，此时下载的内容还在body里面，吊桶该方法将body存到SD卡上，返回true则保存成功
                try {
                    if (DownUtils.writeFileToDisk(response.body(), fileName)) {
                        LogUtil.d("数值", " 写入文件成功");
                    }
                    mHandler.sendEmptyMessage(101);
                } catch (Exception e) {
                    LogUtil.d("数值", "onResponse: " + e);
//                    updateDialog.dismiss();
                    showToast("下载异常,可能服务器并未有该文件存在");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // 甩锅大法好
                showToast("更新失败，网络异常");
                mHandler.sendEmptyMessage(102);
            }
        });
    }


    private void initProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setProgressStyle(android.R.style.Holo_Light_ButtonBar_AlertDialog);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setIndeterminate(false);
            progressDialog.setMax(100);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("下载中");
        }
        progressDialog.show();
    }


    /**
     * 设置进度
     */
    private void setDialogProgress(int progress) {
        if (progressDialog != null) {
            progressDialog.setProgress(progress);
        }
    }

    /**
     * 取消
     */
    private void dismissDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }


    /**
     * 打开安装包
     */
    private void openAPKFile() {
        String mimeDefault = "application/vnd.android.package-archive";
        File apkFile = null;
        if (!TextUtils.isEmpty(fileName)) {
            //mApkUri是apk下载完成后在本地的存储路径
            apkFile = new File(Uri.parse(fileName).getPath());
        }
        if (apkFile == null) {
            return;
        }

        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //兼容7.0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                //这里牵涉到7.0系统中URI读取的变更
                Uri contentUri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", apkFile);
                intent.setDataAndType(contentUri, mimeDefault);
                //兼容8.0
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    boolean hasInstallPermission = getPackageManager().canRequestPackageInstalls();
                    if (!hasInstallPermission) {
                        startInstallPermissionSettingActivity();
                        return;
                    }
                }
            } else {
                intent.setDataAndType(Uri.fromFile(apkFile), mimeDefault);
            }
            if (getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
                //如果APK安装界面存在，携带请求码跳转。使用forResult是为了处理用户 取消 安装的事件。外面这层判断理论上来说可以不要，但是由于国内的定制，这个加上还是比较保险的
                startActivityForResult(intent, APK_INSTALL_BACK_2);//安装页面
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 跳转到设置-允许安装未知来源-页面
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startInstallPermissionSettingActivity() {
        //后面跟上包名，可以直接跳转到对应APP的未知来源权限设置界面。使用startActivityForResult 是为了在关闭设置界面之后，获取用户的操作结果，然后根据结果做其他处理
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, APK_INSTALL_BACK_1);//跳转到权限获取页面
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == APK_INSTALL_BACK_1) {
                openAPKFile();
            }
        } else {
            if (requestCode == APK_INSTALL_BACK_1) {
                //CnPeng 2018/8/2 下午4:31 8.0手机位置来源安装权限
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    boolean hasInstallPermission = getPackageManager().canRequestPackageInstalls();
                    if (!hasInstallPermission) {
                        LogUtil.e("数值", "没有赋予 未知来源安装权限");
                        showUnKnowResourceDialog();
                    }
                }
            } else if (requestCode == APK_INSTALL_BACK_2) {
                //  在安装页面中退出安装了
                if (isForceUpdate) {
                    showApkInstallDialog();
                }
            }
        }
    }


    /**
     * 作者：CnPeng
     * 时间：2018/8/2 下午5:50
     * 功用：未知来源权限弹窗
     * 说明：8.0系统中升级APK时，如果跳转到了 未知来源权限设置界面，并且用户没用允许该权限，会弹出此窗口
     */
    private void showUnKnowResourceDialog() {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(this);
        normalDialog.setTitle("权限获取");
        normalDialog.setMessage("更新安装应用，需要获取您的权限");
        if (isForceUpdate) {
            normalDialog.setCancelable(false);
        }
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //兼容8.0
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            boolean hasInstallPermission = getPackageManager().canRequestPackageInstalls();
                            if (!hasInstallPermission) {
                                startInstallPermissionSettingActivity();
                            }
                        }
                    }
                });
        if (!isForceUpdate) {
            normalDialog.setNegativeButton("取消",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
        }

        // 显示
        normalDialog.show();
    }


    /**
     * 在安装页面中退出安装了
     * 强制更新，必须安装
     */
    private void showApkInstallDialog() {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(this);
        normalDialog.setTitle("版本升级");
        normalDialog.setMessage("请升级安装您的版本");
        normalDialog.setCancelable(false);
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //再次回到安装界面
                        openAPKFile();
                    }
                });
        // 显示
        normalDialog.show();

    }
}
