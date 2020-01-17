package com.example.green.ui.activity.mine.wallet;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.green.R;
import com.example.green.base.BaseMvpActivity;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.mine.QueryPropertybean;
import com.example.green.bean.mine.wallet.WithdrawAppforbean;
import com.example.green.bean.register.AccquireSmsbean;
import com.example.green.config.ApiConfig;
import com.example.green.config.LoadConfig;
import com.example.green.customs.RoundCornerDialog;
import com.example.green.local_utils.SPUtils;
import com.example.green.model.MineModel;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.OnClick;

public class WithdrawActivity extends BaseMvpActivity<CommonPresenter, MineModel>
        implements ICommonView {

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tixian_record)
    TextView mRecord;
    @BindView(R.id.jifen)
    TextView mJifen;
    @BindView(R.id.low_withdraw)
    TextView mLowWithDraw;
    @BindView(R.id.all_jifen)
    TextView mAllJifen;
    @BindView(R.id.et_jifen)
    EditText mEtJifen;
    @BindView(R.id.procedure)
    TextView mProcedure;
    @BindView(R.id.et_code)
    EditText mEtCode;
    @BindView(R.id.acquire_yanzheng)
    TextView mAcquireYanzheng;
    @BindView(R.id.bt_withdraw)
    Button mBtWithdraw;
    private String key;
    private String mMember_mobile;
    private String mMemberbank_name;
    private String mMemberbank_no;
    private String mMemberbank_truename;
    private String mCommission;
    private String mWithdraw;
    private String mAvailable;
    private CountDownTimer mStart;
    private int TYPE = 6;
    private String mAmount;
    private String mCode;

    @Override
    protected void initView() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        key = SPUtils.getInstance().getValue(SPUtils.KEY_USER_TOKEN, "");
    }

    @Override
    protected void initData() {
        mPresenter.getData(ApiConfig.QUERY_PROPERT, key);
    }

    @Override
    protected MineModel initModel() {
        return new MineModel();
    }

    @Override
    protected CommonPresenter initPresenter() {
        return new CommonPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_withdraw;
    }

    @Override
    public void onError(Throwable e) {

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
            case ApiConfig.QUERY_PROPERT:
                QueryPropertybean queryPropertybean = (QueryPropertybean) t[0];
                if (null != queryPropertybean && queryPropertybean.getCode().equals("200")) {
                    double awable = queryPropertybean.getResult().getAwable();
                    // 可转
                    mAvailable = queryPropertybean.getResult().getAvailable();
                    // 最低提现积分
                    mWithdraw = queryPropertybean.getResult().getWithdraw();
                    // 手续费比例
                    mCommission = queryPropertybean.getResult().getCommission();
                    QueryPropertybean.ResultBean.BankinfoBean bankinfo = queryPropertybean.getResult().getBankinfo();
                    if (null != bankinfo) {
                        mMember_mobile = bankinfo.getMember_mobile();
                        mMemberbank_name = bankinfo.getMemberbank_name();
                        mMemberbank_no = bankinfo.getMemberbank_no();
                        mMemberbank_truename = bankinfo.getMemberbank_truename();
                    }
                    if (awable == 0) {
                        mBtWithdraw.setTextColor(getResources().getColor(R.color.c_242424));
                        mBtWithdraw.setClickable(false);
                        mBtWithdraw.setBackgroundResource(R.drawable.commit_bg);
                        toastActivity("可转积分余额不足!!!");
                    }
                    mJifen.setText(mAvailable);
                    mLowWithDraw.setText("最低提现" + mWithdraw + "积分");
                    mProcedure.setText("手续费：" + mCommission + "%");
                } else if (queryPropertybean.getCode().equals("10086")) {
                    showInfoDialog(queryPropertybean.getMessage());
                }
                break;
            case ApiConfig.WITHDRAW_APPLY:
                WithdrawAppforbean withdrawAppforbean = (WithdrawAppforbean) t[0];
                if (null != withdrawAppforbean && withdrawAppforbean.getCode().equals("200")) {
                    showConfirmDialog();
                    mPresenter.getData(ApiConfig.QUERY_PROPERT, key);
                } else {
                    toastActivity(withdrawAppforbean.getMessage());
                }
                break;
        }
    }

    private void showInfoDialog(String msg) {
        View view = View.inflate(this, R.layout.dialog_withdraw_confirm, null);
        final RoundCornerDialog roundCornerDialog = new RoundCornerDialog(this, 0, 0, view, R.style.RoundCornerDialog);
        roundCornerDialog.show();
        roundCornerDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        roundCornerDialog.setOnKeyListener(keylistener);//设置点击返回键Dialog不消失

        TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
        TextView tv_logout_confirm = (TextView) view.findViewById(R.id.tv_logout_confirm);
        tv_message.setText(msg);
        // 确定
        tv_logout_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                roundCornerDialog.dismiss();
                finish();
            }
        });
    }

    @OnClick({R.id.back, R.id.tixian_record, R.id.all_jifen, R.id.acquire_yanzheng, R.id.bt_withdraw})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                finish();
                break;
            case R.id.tixian_record:
                //设置切换动画，从右边进入，左边退出
                overridePendingTransition(R.anim.in_from_right,
                        R.anim.out_to_left);
                startActivity(new Intent(WithdrawActivity.this, WithdrawRecordActivity.class));
                break;
            case R.id.all_jifen:
                mEtJifen.setText(mAvailable);
                break;
            case R.id.acquire_yanzheng:
                mStart = new CountDownTimer(60000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        mAcquireYanzheng.setText("重新发送" + "(" + millisUntilFinished / 1000 + "s)");
                        mAcquireYanzheng.setClickable(false);
                    }

                    @Override
                    public void onFinish() {
                        mAcquireYanzheng.setClickable(true);
                        mAcquireYanzheng.setText("获取验证码");
                        mAcquireYanzheng.setClickable(true);
                    }
                }.start();
                mPresenter.getData(ApiConfig.ACCQUIRE_CODE, mMember_mobile, TYPE, LoadConfig.NORMAL);
                break;
            case R.id.bt_withdraw:
                hideInput();
                mAmount = mEtJifen.getText().toString().trim();
                mCode = mEtCode.getText().toString().trim();

               /* 比较不同类型大小
               BigDecimal bigDecimal = new BigDecimal(amount);// 输入金额
               int result = bigDecimal.compareTo(new BigDecimal(mWithdraw));  // 最低提现金额
                if (1 == result) {//A>B

                } else if (0 == result) {//A=B

                } else if (-1 == result) {//A<B

                }
                */
                if (!TextUtils.isEmpty(mAmount) && !TextUtils.isEmpty(mCode)) {
                    BigDecimal bigDecimal = new BigDecimal(mAmount);// 输入金额
                    int result = bigDecimal.compareTo(new BigDecimal(mWithdraw));  // 最低提现金额
                    if (1 == result || 0 == result) {
                        showHintDialog();

                    } else {
                        toastActivity("输入大小不能低于最低提现积分");
                    }
                } else {
                    toastActivity("请填写完整信息");
                }
                break;
        }
    }


    private void showHintDialog() {
        View view = View.inflate(this, R.layout.dialog_withdraw_hint, null);
        final RoundCornerDialog roundCornerDialog = new RoundCornerDialog(this, 0, 0, view, R.style.RoundCornerDialog);
        roundCornerDialog.show();
        roundCornerDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        roundCornerDialog.setOnKeyListener(keylistener);//设置点击返回键Dialog不消失

        TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
        TextView tv_logout_confirm = (TextView) view.findViewById(R.id.tv_logout_confirm);
        TextView tv_logout_cancel = (TextView) view.findViewById(R.id.tv_logout_cancel);
        tv_message.setText("您本次申请提现：" + mAmount + ",手续费为：" + Double.parseDouble(mAmount) * (Double.parseDouble(mCommission) / 100));

        //确定
        tv_logout_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roundCornerDialog.dismiss();
                mPresenter.getData(ApiConfig.WITHDRAW_APPLY, key, mAmount, mCommission, mMemberbank_name, mMemberbank_no, mMemberbank_truename, mCode);
            }
        });
        //取消
        tv_logout_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roundCornerDialog.dismiss();
            }
        });
    }

    private void showConfirmDialog() {
        View view = View.inflate(this, R.layout.dialog_withdraw_confirm, null);
        final RoundCornerDialog roundCornerDialog = new RoundCornerDialog(this, 0, 0, view, R.style.RoundCornerDialog);
        roundCornerDialog.show();
        roundCornerDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        roundCornerDialog.setOnKeyListener(keylistener);//设置点击返回键Dialog不消失

        TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
        TextView tv_logout_confirm = (TextView) view.findViewById(R.id.tv_logout_confirm);
        tv_message.setText("提交申请成功");

        //确定
        tv_logout_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roundCornerDialog.dismiss();
                finish();
            }
        });
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

    /**
     * 隐藏键盘
     */
    protected void hideInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View v = getWindow().peekDecorView();
        if (null != v) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
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
