package com.example.green.ui.activity.mine.wallet;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.example.green.R;
import com.example.green.base.BaseActivity;
import com.example.green.base.BaseMvpActivity;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.pay.AcquirePayCodebean;
import com.example.green.config.ApiConfig;
import com.example.green.local_utils.SPUtils;
import com.example.green.model.MineModel;
import com.example.green.pay.alipay.Alipay;
import com.example.green.pay.weixin.WXPay;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

public class RechargeActivity extends BaseMvpActivity<CommonPresenter, MineModel>
        implements ICommonView {

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.chongzhi_jine)
    RadioGroup mRadioGroup;
    @BindView(R.id.wushi)
    RadioButton mWushi;
    @BindView(R.id.yibai)
    RadioButton mYibai;
    @BindView(R.id.wubai)
    RadioButton mWubai;
    @BindView(R.id.yiqian)
    RadioButton mYiqian;
    @BindView(R.id.et_jine)
    EditText mEtJine;
    @BindView(R.id.rl_zhifubao)
    RelativeLayout mRlZhifubao;
    @BindView(R.id.rl_weixin)
    RelativeLayout mRlWeixin;
    @BindView(R.id.rl_yinlian)
    RelativeLayout mRlYinlian;
    @BindView(R.id.recharge_jine)
    TextView mRechargeJine;
    @BindView(R.id.bt_recharge)
    Button mBtRecharge;

    private String payMode;
    private String pdr_amount;
    private String key;
    // 支付中加载
    ProgressDialog progressDialog;
    private JSONObject mJsonObject;
    private static final String TAG = "RechargeActivity";

    private static class MyHandler extends Handler {
        private final WeakReference<RechargeActivity> mActivity;

        public MyHandler(RechargeActivity activity) {
            mActivity = new WeakReference<RechargeActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            RechargeActivity activity = mActivity.get();
            if (activity != null) {
//                activity.mPresenter.getData(ApiConfig.URL_ORDERSTATUS, activity.mOrderId, ++activity.time);
            }
        }
    }

    private final MyHandler mHandler = new MyHandler(this);

    @Override
    protected void initView() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mWushi.setChecked(true);
        mRlZhifubao.setSelected(true);
        pdr_amount = "50";
        payMode = "alipay_app";
        mEtJine.addTextChangedListener(jineEditInput); // 监听手机号输入状态
    }

    private TextWatcher jineEditInput = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        //一般我们都是在这个里面进行我们文本框的输入的判断，上面两个方法用到的很少
        @Override
        public void afterTextChanged(Editable s) {
            mRadioGroup.clearCheck();
            pdr_amount = mEtJine.getText().toString().trim();
            mRechargeJine.setText(Html.fromHtml("&yen; ") + mEtJine.getText().toString().trim());
        }
    };

    @Override
    protected void initData() {
        key = SPUtils.getInstance().getValue(SPUtils.KEY_USER_TOKEN, "");
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
        return R.layout.activity_recharge;
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.GETPAYMENT:
                AcquirePayCodebean acquirePayCodebean = (AcquirePayCodebean) t[0];
                if (null != acquirePayCodebean && acquirePayCodebean.getCode() == 10000) {
                    String content = acquirePayCodebean.getResult().getContent(); // 支付参数
                    if (payMode.equals("alipay_app")) {
                        doAlipay(content);
                    } else if (payMode.equals("wxpay_app")) {
                        mJsonObject = JSONObject.parseObject(content);
                        //                        toWXPay();

                        String param = content.replace("\n", "");
                        Log.e(TAG, "param: " + param);

                        doWXPay(param);
                    }
                } else {
                    toastActivity(acquirePayCodebean.getMessage());
                }
                break;
        }
    }

    @OnClick({R.id.back, R.id.wushi, R.id.yibai, R.id.wubai, R.id.yiqian, R.id.rl_zhifubao, R.id.rl_weixin, R.id.rl_yinlian, R.id.bt_recharge})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                finish();
                break;
            case R.id.wushi:
                pdr_amount = "50";
                mRechargeJine.setText(Html.fromHtml("&yen; ") + pdr_amount);
                break;
            case R.id.yibai:
                pdr_amount = "100";
                mRechargeJine.setText(Html.fromHtml("&yen; ") + pdr_amount);
                break;
            case R.id.wubai:
                pdr_amount = "500";
                mRechargeJine.setText(Html.fromHtml("&yen; ") + pdr_amount);
                break;
            case R.id.yiqian:
                pdr_amount = "1000";
                mRechargeJine.setText(Html.fromHtml("&yen; ") + pdr_amount);
                break;
            case R.id.rl_zhifubao:
                payMode = "alipay_app";
                mRlZhifubao.setSelected(true);
                mRlWeixin.setSelected(false);
                mRlZhifubao.setBackground(getResources().getDrawable(R.drawable.pay_select));
                mRlWeixin.setBackground(getResources().getDrawable(R.drawable.pay_unselect));
                break;
            case R.id.rl_weixin:
                payMode = "wxpay_app";
                mRlZhifubao.setSelected(false);
                mRlWeixin.setSelected(true);
                mRlZhifubao.setBackground(getResources().getDrawable(R.drawable.pay_unselect));
                mRlWeixin.setBackground(getResources().getDrawable(R.drawable.pay_select));
                break;
            case R.id.rl_yinlian:
                startActivity(new Intent(RechargeActivity.this, RemittanceActivity.class));
                break;
            case R.id.bt_recharge:
                mPresenter.getData(ApiConfig.GETPAYMENT, key, payMode, pdr_amount);
                break;
        }
    }

    /**
     * 支付宝支付
     *
     * @param pay_param 支付服务生成的支付参数
     */
    private void doAlipay(String pay_param) {
        new Alipay(this, pay_param, new Alipay.AlipayResultCallBack() {
            @Override
            public void onSuccess() {
                //阿里支付成功 发送广播 充值成功
                LocalBroadcastManager.getInstance(RechargeActivity.this)
                        .sendBroadcast(new Intent(BaseActivity.RECHARGE_SUCCESS));
                Toast.makeText(getApplication(), "支付成功", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onDealing() {
                initProgressDialog();//阿里支付成功，支付处理中  开始轮询
                Toast.makeText(getApplication(), "支付处理中...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case Alipay.ERROR_RESULT:
                        Toast.makeText(getApplication(), "支付失败:支付结果解析错误", Toast.LENGTH_SHORT).show();
                        break;

                    case Alipay.ERROR_NETWORK:
                        Toast.makeText(getApplication(), "支付失败:网络连接错误", Toast.LENGTH_SHORT).show();
                        break;

                    case Alipay.ERROR_PAY:
                        Toast.makeText(getApplication(), "支付错误:支付码支付失败", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        Toast.makeText(getApplication(), "支付错误", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplication(), "支付取消", Toast.LENGTH_SHORT).show();
            }
        }).doPay();
    }

    /**
     * 微信支付
     *
     * @param pay_param 支付服务生成的支付参数
     */
    private void doWXPay(String pay_param) {
        String wx_appid = "wxb53dbb4718418f8e";     //替换为自己的appid
        WXPay.init(getApplicationContext(), wx_appid);      //要在支付前调用
        WXPay.getInstance().doPay(pay_param, new WXPay.WXPayResultCallBack() {
            @Override
            public void onSuccess() {
                //微信支付成功  发送广播 充值成功
                LocalBroadcastManager.getInstance(RechargeActivity.this)
                        .sendBroadcast(new Intent(BaseActivity.RECHARGE_SUCCESS));
                Toast.makeText(getApplication(), "支付成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case WXPay.NO_OR_LOW_WX:
                        Toast.makeText(getApplication(), "未安装微信或微信版本过低", Toast.LENGTH_SHORT).show();
                        break;

                    case WXPay.ERROR_PAY_PARAM:
                        Toast.makeText(getApplication(), "参数错误", Toast.LENGTH_SHORT).show();
                        break;

                    case WXPay.ERROR_PAY:
                        Toast.makeText(getApplication(), "支付失败", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplication(), "支付取消", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private IWXAPI iwxapi; //微信支付api

    /**
     * 调起微信支付的方法
     **/
    private void toWXPay() {
        iwxapi = WXAPIFactory.createWXAPI(this, null); //初始化微信api
        iwxapi.registerApp(mJsonObject.getString("appid")); //注册appid appid可以在开发平台获取

        Runnable payRunnable = new Runnable() {  //这里注意要放在子线程
            @Override
            public void run() {
                PayReq request = new PayReq(); //调起微信APP的对象
                //下面是设置必要的参数，也就是前面说的参数,这几个参数从何而来请看上面说明
                request.appId = mJsonObject.getString("appid");
                request.partnerId = mJsonObject.getString("mch_id");
                request.prepayId = mJsonObject.getString("prepay_id");
                request.packageValue = "Sign=WXPay";
                request.nonceStr = mJsonObject.getString("nonce_str");
                request.timeStamp = mJsonObject.getString("timestamp");
                request.sign = mJsonObject.getString("sign");
                iwxapi.sendReq(request);//发送调起微信的请求
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    private void initProgressDialog() {
        progressDialog = new ProgressDialog(RechargeActivity.this);
        progressDialog.setIndeterminate(false);//循环滚动
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("支付中");
        progressDialog.show();
        progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
//                mHandler.removeMessages(HANDLER_WHAT);
            }
        });

    }

    /**
     * 取消
     */
    private void dismissDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
