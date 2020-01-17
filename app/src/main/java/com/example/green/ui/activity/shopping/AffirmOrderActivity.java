package com.example.green.ui.activity.shopping;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.green.R;
import com.example.green.adapter.shoptrolley.OrderStoreItemAdapter;
import com.example.green.base.BaseActivity;
import com.example.green.base.BaseMvpActivity;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.pay.AcquireOrderInfobean;
import com.example.green.bean.pay.CreateOrderbean;
import com.example.green.bean.pay.ShoppingInfobean;
import com.example.green.config.ApiConfig;
import com.example.green.config.LoadConfig;
import com.example.green.customs.RoundCornerDialog;
import com.example.green.local_utils.SPUtils;
import com.example.green.model.ShopModel;
import com.example.green.ui.activity.PayModeActivity;
import com.example.green.ui.activity.mine.ShoppingAddressActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AffirmOrderActivity extends BaseMvpActivity<CommonPresenter, ShopModel>
        implements ICommonView {

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_site)
    TextView mTvSite;
    @BindView(R.id.person_name)
    TextView mPersonName;
    @BindView(R.id.person_phone)
    TextView mPersonPhone;
    @BindView(R.id.rl_site)
    RelativeLayout mRlSite;
    @BindView(R.id.rl_replace)
    RelativeLayout mRl_Replace;
    @BindView(R.id.rl_address_info)
    RelativeLayout mRl_address;
    @BindView(R.id.order_goods_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.bt_commit_order)
    Button mBtCommitOrder;
    @BindView(R.id.order_price)
    TextView mOrderPrice;

    private String cart_id;
    private String ifCart;
    private String mAddressId;
    private String offpay_hash = "";
    private String offpay_hash_batch = "";
    private String pay_name = "online";
    private String invoice_id = "0";
    private String voucher = "0|1|0";
    private String key;
    private static final String TAG = "AffirmOrderActivity";
    private String mVat_hash;
    private int SELECT = 1; // 选择地址
    private List<ShoppingInfobean.ResultBean.StoreCartListApiBean> mCartListApiBeans;
    private OrderStoreItemAdapter mOrderStoreItemAdapter;

    @Override
    protected void initView() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        // 注册 关闭activity 广播
        CloseActivityReceiver closeReceiver = new CloseActivityReceiver();
        IntentFilter intentFilter = new IntentFilter("con.lcry.close.AffirmActivity");
        registerReceiver(closeReceiver, intentFilter);


        key = SPUtils.getInstance().getValue(SPUtils.KEY_USER_TOKEN, "");
        Intent intent = getIntent();
        cart_id = intent.getStringExtra("cart_id");
        ifCart = intent.getStringExtra("ifCart"); // 1为从购物车购买，空为直接购买

        mCartListApiBeans = new ArrayList<>();
        mOrderStoreItemAdapter = new OrderStoreItemAdapter(R.layout.layout_order_store_item, mCartListApiBeans);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mOrderStoreItemAdapter);

    }

    /**
     * 实现Activity的广播接收
     *
     * @author LCry
     */
    public class CloseActivityReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            AffirmOrderActivity.this.finish();
        }
    }

    @Override
    protected void initData() {
        mPresenter.getData(ApiConfig.SHOPPING_STEP_ONE, key, cart_id, ifCart); // 第一步 生成购物信息
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
        return R.layout.activity_affirm_order;
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError: " + e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.SHOPPING_STEP_ONE:
                ShoppingInfobean shoppingInfobean = (ShoppingInfobean) t[0];
                if (null != shoppingInfobean && shoppingInfobean.getCode().equals("200")) {

                    List<ShoppingInfobean.ResultBean.StoreCartListApiBean> store_cart_list_api = shoppingInfobean.getResult().getStore_cart_list_api();
                    mCartListApiBeans.addAll(store_cart_list_api);
                    mOrderStoreItemAdapter.notifyDataSetChanged();

                    String order_amount = shoppingInfobean.getResult().getOrder_amount();
                    mOrderPrice.setText(Html.fromHtml("&yen; ") + order_amount);
                    mVat_hash = shoppingInfobean.getResult().getVat_hash();
                    ShoppingInfobean.ResultBean.AddressInfoBean address_info = shoppingInfobean.getResult().getAddress_info();
                    if (address_info.getAddress_id() != null) {
                        mRl_Replace.setVisibility(View.GONE);
                        mRl_address.setVisibility(View.VISIBLE);
                        mAddressId = address_info.getAddress_id();
                        mTvSite.setText(address_info.getArea_info() + " " + address_info.getAddress_detail());
                        mPersonName.setText(address_info.getAddress_realname());
                        mPersonPhone.setText(address_info.getAddress_mob_phone());
                    } else {
                        mRl_Replace.setVisibility(View.VISIBLE);
                        mRl_address.setVisibility(View.GONE);
                        toastActivity("请选择收货地址");
                    }
                } else {
                    showInfoDialog(shoppingInfobean.getMessage());
                }
                break;
            case ApiConfig.SHOPPING_STEP_TWO:
                CreateOrderbean createOrderbean = (CreateOrderbean) t[0];
                if (null != createOrderbean && createOrderbean.getCode().equals("200")) {
                    // 订单提交 发送广播 更新购物车列表
                    LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(BaseActivity.SUBMIT_ORDER));
                    String pay_sn = createOrderbean.getResult().getPay_sn();

                    mPresenter.getData(ApiConfig.SHOPPING_THIRD, key, pay_sn);
                } else {
                    toastActivity(createOrderbean.getMessage());
                }
                break;
            case ApiConfig.SHOPPING_THIRD:
                AcquireOrderInfobean acquireOrderInfobean = (AcquireOrderInfobean) t[0];
                if (null != acquireOrderInfobean && acquireOrderInfobean.getCode().equals("200")) {
                    AcquireOrderInfobean.ResultBean.PayInfoBean pay_info = acquireOrderInfobean.getResult().getPay_info();
                    String member_available_pd = pay_info.getMember_available_pd(); // 用户可用储值卡余额
                    String pay_sn = pay_info.getPay_sn(); // 支付单号
                    String pay_amount = pay_info.getPay_amount(); // 订单价格

                    Intent intent = new Intent(AffirmOrderActivity.this, PayModeActivity.class);
                    intent.putExtra("pay_sn", pay_sn);
                    intent.putExtra("pay_amount", pay_amount);
                    intent.putExtra("member_available_pd", member_available_pd);
                    intent.putExtra("type", 1);
                    startActivity(intent);
                } else {
                    toastActivity(acquireOrderInfobean.getMessage());
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

    @OnClick({R.id.back, R.id.rl_site, R.id.bt_commit_order})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                finish();
                break;
            case R.id.rl_site:
                Intent intent_site = new Intent(this, ShoppingAddressActivity.class);
                intent_site.putExtra("tag", SELECT);
                startActivityForResult(intent_site, 0);//此处的requestCode应与下面结果处理函中调用的requestCode一致
                break;
            case R.id.bt_commit_order:// 提交订单
                if (null != mAddressId) {
                    mRl_Replace.setVisibility(View.GONE);
                    mRl_address.setVisibility(View.VISIBLE);

                    mPresenter.getData(ApiConfig.SHOPPING_STEP_TWO, key,
                            ifCart, cart_id, mAddressId, mVat_hash, offpay_hash, offpay_hash_batch, pay_name, invoice_id, voucher, LoadConfig.NORMAL); // 第二步 生成订单

                } else {
                    mRl_Replace.setVisibility(View.VISIBLE);
                    mRl_address.setVisibility(View.GONE);
                    toastActivity("请选择收货地址");
                }
                break;
        }
    }

    //结果处理函数，当从secondActivity中返回时调用此函数
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            mRl_Replace.setVisibility(View.GONE);
            mRl_address.setVisibility(View.VISIBLE);
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                String area = bundle.getString("area");
                String site = bundle.getString("site");
                String name = bundle.getString("name");
                String phone = bundle.getString("phone");
                mAddressId = bundle.getString("addressId");

                mTvSite.setText(area + site);
                mPersonName.setText(name);
                mPersonPhone.setText(phone);
            }
        }
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
}
