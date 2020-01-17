package com.example.green.ui.activity;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.green.R;
import com.example.green.base.BaseActivity;
import com.example.green.base.BaseMvpActivity;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.pay.PayOrderInfobean;
import com.example.green.config.ApiConfig;
import com.example.green.local_utils.MyDialog;
import com.example.green.local_utils.SPUtils;
import com.example.green.model.ShopModel;
import com.example.green.ui.activity.shopping.PaymentSuccessActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class PayModeActivity extends BaseMvpActivity<CommonPresenter, ShopModel>
        implements ICommonView, MyDialog.OnCenterItemClickListener {

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.order_sum)
    TextView mOrderSum;
    @BindView(R.id.balance)
    TextView mBalance;
    @BindView(R.id.check_xianjin)
    RadioButton mCheckXianjin;
    @BindView(R.id.bt_payment)
    Button mPayment;
    private String key = SPUtils.getInstance().getValue(SPUtils.KEY_USER_TOKEN, "");
    private String pay_sn;
    private String pay_amount;
    private String mMember_available_pd;
    private String password;
    private String pd_pay = "1";
    private String payment_code = "predeposit";
    private static final String TAG = "PayModeActivity";
    private MyDialog mMyDialog;
    private EditText mEditText;
    private int TYPE;

    @Override
    protected void initView() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        Intent intent = getIntent();
        pay_sn = intent.getStringExtra("pay_sn");
        pay_amount = intent.getStringExtra("pay_amount");
        mMember_available_pd = intent.getStringExtra("member_available_pd");
        TYPE = intent.getIntExtra("type", 0);

        mOrderSum.setText(pay_amount + "元");
        mBalance.setText("(余额:" + mMember_available_pd + "元)");//        (余额:1959.90)
        mCheckXianjin.setChecked(true);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected ShopModel initModel() {
        return new ShopModel();
    }

    @Override
    protected CommonPresenter initPresenter() {
        return new CommonPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay_mode;
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError: " + e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.SHOPPING_FOURTH:
                PayOrderInfobean payOrderInfobean = (PayOrderInfobean) t[0];
                if (null != payOrderInfobean && payOrderInfobean.getCode().equals("200")) {
                    if (payOrderInfobean.getResult().getPay_end() == 1) { // 支付完成
                        // 付款成功 发送广播
                        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(BaseActivity.PAY_SUCCESS));
                        Intent intent = new Intent(PayModeActivity.this, PaymentSuccessActivity.class);
                        intent.putExtra("payment", pay_amount);
                        intent.putExtra("pay_sn", payOrderInfobean.getResult().getPay_sn());
                        intent.putExtra("type", TYPE);
                        intent.putExtra("draw", payOrderInfobean.getResult().getDraw());
                        startActivity(intent);
                        finish();
                    }
                } else {
                    toastActivity(payOrderInfobean.getMessage());
                }
                break;
        }
    }

    @OnClick({R.id.back, R.id.check_xianjin, R.id.bt_payment})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                finish();
                break;
            case R.id.check_xianjin:
                mCheckXianjin.setChecked(true);
                break;
            case R.id.bt_payment:
                show();
                break;
        }
    }

    private void show() {
        mMyDialog = new MyDialog(this, R.layout.layout_dialog_paypsw, new int[]
                {R.id.bt_confirm_psw});
        mMyDialog.setOnCenterItemClickListener(this);
        mMyDialog.setCanceledOnTouchOutside(false);// 设置外部点击消失
        mMyDialog.show();
    }

    @Override
    public void OnCenterItemClick(MyDialog dialog, View view) {

        switch (view.getId()) {
            case R.id.bt_confirm_psw:
                mEditText = mMyDialog.findViewById(R.id.et_paypsw);
                password = mEditText.getText().toString().trim();
                mPresenter.getData(ApiConfig.SHOPPING_FOURTH, key, pay_sn, password, pd_pay, payment_code); // 第四步 购买
                Log.e(TAG, "OnCenterItemClick: " + pay_sn + "----" + pay_amount + "----" + password + "----" + pd_pay + "----" + payment_code);
                break;
        }
    }
}
