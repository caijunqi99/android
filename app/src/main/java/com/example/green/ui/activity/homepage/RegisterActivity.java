package com.example.green.ui.activity.homepage;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.green.R;
import com.example.green.base.BaseMvpActivity;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.register.AccquireSmsbean;
import com.example.green.bean.register.RegisterDatabean;
import com.example.green.config.ApiConfig;
import com.example.green.config.LoadConfig;
import com.example.green.local_utils.MyDialog;
import com.example.green.local_utils.SPUtils;
import com.example.green.model.UserModel;
import com.example.green.ui.activity.MainActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseMvpActivity<CommonPresenter, UserModel>
        implements ICommonView, MyDialog.OnCenterItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.user_phone)
    EditText mUserPhone;
    @BindView(R.id.user_code)
    EditText mUserCode;
    @BindView(R.id.acquire_code)
    TextView mAcquireCode;
    @BindView(R.id.user_psw)
    EditText mUserPsw;
    @BindView(R.id.user_psw_again)
    EditText mUserPswAgain;
    @BindView(R.id.user_invitation_code)
    EditText mUserInvitationCode;
    @BindView(R.id.check)
    ImageView mCheck;

    private int TYPE = 1; // 注册
    private MyDialog mMyDialog;
    private static final String TAG = "RegisterActivity";
    private CountDownTimer mStart;

    @Override
    protected void initView() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        mUserPhone.addTextChangedListener(phoneEditInput); // 监听手机号输入状态
        mUserCode.addTextChangedListener(codeEditInput); // 监听验证码输入状态
    }

    private TextWatcher phoneEditInput = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        //一般我们都是在这个里面进行我们文本框的输入的判断，上面两个方法用到的很少
        @Override
        public void afterTextChanged(Editable s) {
            String phone = mUserPhone.getText().toString().trim();
            Pattern pattern = Pattern.compile("\\d{11}");
            Matcher m = pattern.matcher(phone);
            if (m.matches()) {
                mUserCode.requestFocus(); // 获取焦点 光标出现
            }
        }
    };
    private TextWatcher codeEditInput = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        //一般我们都是在这个里面进行我们文本框的输入的判断，上面两个方法用到的很少
        @Override
        public void afterTextChanged(Editable s) {
            String code = mUserCode.getText().toString().trim();
            Pattern pattern = Pattern.compile("[0-9]{6}");
            Matcher m = pattern.matcher(code);
            if (m.matches()) {
                mUserPsw.requestFocus(); // 获取焦点 光标出现
            }
        }
    };

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
        return R.layout.activity_register;
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError: " + e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.ACCQUIRE_CODE:
                AccquireSmsbean accquireSmsbean = (AccquireSmsbean) t[0];
                if (null != accquireSmsbean && accquireSmsbean.getCode().equals("200")) {
                    toastActivity("发送成功");
                }
                break;
            case ApiConfig.REGISTER:
                RegisterDatabean registerDatabean = (RegisterDatabean) t[0];
                Log.e(TAG, "注册信息======: " + registerDatabean.getMessage());
                if (null != registerDatabean && registerDatabean.getCode().equals("200")) {
                    RegisterDatabean.ResultBean result = registerDatabean.getResult();
                    String key = result.getKey(); // token
                    int userid = result.getUserid();// userid
                    // 保存
                    SPUtils.getInstance().setValue(SPUtils.KEY_USER_TOKEN, key);
                    SPUtils.getInstance().setValue(SPUtils.KEY_USER_ID, userid);
                    SPUtils.getInstance().setValue(SPUtils.KEY_USER_NAME, mUserPhone.getText().toString().trim());
                    SPUtils.getInstance().setValue(SPUtils.KEY_PASSWORD, mUserPsw.getText().toString().trim());
                    show(); // 注册成功
                } else {
                    toastActivity(registerDatabean.getMessage());
                }
                break;
            case ApiConfig.LOGIN:
                RegisterDatabean loginbean = (RegisterDatabean) t[0];
                if (null != loginbean && loginbean.getCode().equals("200")) {
                    String key = loginbean.getResult().getKey();
                    int userid = loginbean.getResult().getUserid();// userid
                    SPUtils.getInstance().setValue(SPUtils.KEY_USER_TOKEN, key); // 保存token
                    SPUtils.getInstance().setValue(SPUtils.KEY_USER_ID, userid);
                    toastActivity("登录成功");
                    startActivity(new Intent(this, MainActivity.class));
                }
                break;
        }

    }

    @OnClick({R.id.back, R.id.acquire_code, R.id.bt_register, R.id.xieyi})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                finish();
                break;
            case R.id.acquire_code: // 获取验证码
                // 手机号码 正则判断
                String telRegex = "^((13[0-9])|(14[4-9])|(15[^4])|(16[6-7])|(17[^9])|(18[0-9])|(19[1|8|9]))\\d{8}$";
                if (!TextUtils.isEmpty(mUserPhone.getText().toString().trim()) &&
                        mUserPhone.getText().toString().trim().matches(telRegex)) {
                    mStart = new CountDownTimer(60000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            mAcquireCode.setText("重新发送" + "(" + millisUntilFinished / 1000 + "s)");
                            mAcquireCode.setClickable(false);
                        }

                        @Override
                        public void onFinish() {
                            mAcquireCode.setClickable(true);
                            mAcquireCode.setText("获取验证码");
                            mAcquireCode.setClickable(true);
                        }
                    }.start();
                    mPresenter.getData(ApiConfig.ACCQUIRE_CODE, mUserPhone.getText().toString().trim(), TYPE, LoadConfig.NORMAL);
                } else {
                    toastActivity("手机号为空或有误");
                }
                break;
            case R.id.xieyi:
                startActivity(new Intent(RegisterActivity.this, AgreementActivity.class));
                break;
            case R.id.bt_register: // 立即注册
                String pswRegex = "^(?![^a-zA-Z]+$)(?!\\D+$).{6,12}$";
                String psw = mUserPsw.getText().toString().trim();
//                psw.matches(pswRegex);
                String psw_confirm = mUserPswAgain.getText().toString().trim();
                if (!TextUtils.isEmpty(psw)
                        && !TextUtils.isEmpty(psw_confirm)
                        && !TextUtils.isEmpty(mUserCode.getText().toString().trim())
                        && !TextUtils.isEmpty(mUserInvitationCode.getText().toString().trim())
                        ) {
                    Log.e(TAG, "onClick---: " + "sssssssssssssss");
                    mPresenter.getData(ApiConfig.REGISTER, mUserPhone.getText().toString().trim(), psw, psw_confirm, "android",
                            mUserInvitationCode.getText().toString().trim(), mUserCode.getText().toString().trim(), TYPE, LoadConfig.NORMAL);

                } else if (!psw.equals(psw_confirm)) {
                    toastActivity("两次输入密码不一致");
                } else if (TextUtils.isEmpty(mAcquireCode.getText().toString().trim())) {
                    toastActivity("验证码不能为空");
                } else if (TextUtils.isEmpty(mUserPhone.getText().toString().trim())) {
                    toastActivity("手机号不能为空");
                } else if (TextUtils.isEmpty(psw)) {
                    toastActivity("密码不能为空");
                } else if (TextUtils.isEmpty(psw_confirm)) {
                    toastActivity("确认密码不能为空");
                } else if (TextUtils.isEmpty(mUserInvitationCode.getText().toString().trim())) {
                    toastActivity("邀请码不能为空");
                }
                break;
        }
    }

    private void show() {
        mMyDialog = new MyDialog(this, R.layout.layout_pop_register, new int[]
                {R.id.bt_login});
        mMyDialog.setOnCenterItemClickListener(this);
        mMyDialog.setCanceledOnTouchOutside(false);// 设置外部点击消失
        mMyDialog.show();
    }

    @Override
    public void OnCenterItemClick(MyDialog dialog, View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                mPresenter.getData(ApiConfig.LOGIN, mUserPhone.getText().toString().trim(),
                        mUserPsw.getText().toString().trim(), "android", LoadConfig.NORMAL);
                toastActivity("正在登录...");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mStart != null) {
            mStart.cancel();
        }
    }
}
