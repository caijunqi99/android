package com.example.green.ui.activity.homepage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.green.R;
import com.example.green.base.BaseMvpActivity;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.register.RegisterDatabean;
import com.example.green.config.ApiConfig;
import com.example.green.config.LoadConfig;
import com.example.green.local_utils.SPUtils;
import com.example.green.model.UserModel;
import com.example.green.ui.activity.MainActivity;
import com.gyf.immersionbar.ImmersionBar;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseMvpActivity<CommonPresenter, UserModel>
        implements ICommonView {


    @BindView(R.id.user_phone)
    EditText mUserPhone;
    @BindView(R.id.user_psw)
    EditText mUserPsw;
    final String ACCOUNT_PREF = "account";
    final String PASSWORD_PREF = "password";
    private SharedPreferences mPreference;
    private static final String TAG = "LoginActivity";

    @Override
    protected void initView() {
        ImmersionBar.with(this).init();
        //从 SharedPreferences 中获取【是否记住密码】参数
        mPreference = PreferenceManager.getDefaultSharedPreferences(this);
        mUserPhone.setText(mPreference.getString(ACCOUNT_PREF, ""));
        mUserPsw.setText(mPreference.getString(PASSWORD_PREF, ""));

    }

    @Override
    protected void initData() {

    }

    @Override
    protected UserModel initModel() {
        return new UserModel();
    }

    @Override
    protected CommonPresenter initPresenter() {
        return new CommonPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.LOGIN:
                RegisterDatabean loginbean = (RegisterDatabean) t[0];
                if (null != loginbean && loginbean.getCode().equals("200")) {
                    String key = loginbean.getResult().getKey();
                    int userid = loginbean.getResult().getUserid();
                    SPUtils.getInstance().setValue(SPUtils.KEY_USER_TOKEN, key);
                    SPUtils.getInstance().setValue(SPUtils.KEY_USER_ID, userid);
                    SPUtils.getInstance().setValue(SPUtils.KEY_USER_NAME, mUserPhone.getText().toString().trim());
                    Log.e(TAG, "用户Token-------: " + key); //  token

                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                } else if (null != loginbean && loginbean.getCode().equals("100")) {
                    toastActivity("用户名密码错误");
                }
                break;
        }
    }


    @OnClick({R.id.rl_forget_psw, R.id.rl_register, R.id.login})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.rl_forget_psw:
                startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
                break;
            case R.id.rl_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.login:
                if (isSoftShowing()) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                }
//                String passRegex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
                if (!TextUtils.isEmpty(mUserPhone.getText().toString().trim()) && !TextUtils.isEmpty(mUserPsw.getText().toString().trim())) {
//                    if (mUserPsw.getText().toString().trim().matches(passRegex)) {
                    mPresenter.getData(ApiConfig.LOGIN, mUserPhone.getText().toString().trim(),
                            mUserPsw.getText().toString().trim(), "android", LoadConfig.NORMAL);
                    SharedPreferences.Editor editor = mPreference.edit();
                    editor.putString(ACCOUNT_PREF, mUserPhone.getText().toString().trim());
                    editor.putString(PASSWORD_PREF, mUserPsw.getText().toString().trim());
                    editor.apply();
//                    } else {
//                        toastActivity("密码必须为6-16位数字 字母组合");
//                    }
                } else {
                    toastActivity("用户名密码不能为空！");
                }
                break;
        }
    }

    // 软键盘是否显示
    private boolean isSoftShowing() {
        //获取当前屏幕内容的高度
        int screenHeight = getWindow().getDecorView().getHeight();
        //获取View可见区域的bottom
        Rect rect = new Rect();
        //DecorView即为activity的顶级view
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        //考虑到虚拟导航栏的情况（虚拟导航栏情况下：screenHeight = rect.bottom + 虚拟导航栏高度）
        //选取screenHeight*2/3进行判断
        return screenHeight * 2 / 3 > rect.bottom;
    }
}
