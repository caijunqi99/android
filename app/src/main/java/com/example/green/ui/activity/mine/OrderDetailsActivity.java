package com.example.green.ui.activity.mine;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.green.R;
import com.example.green.adapter.mine.order.OrderDetailsGoodsItemAdapter;
import com.example.green.base.BaseActivity;
import com.example.green.base.BaseMvpActivity;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.mine.ChangeOrderStatebean;
import com.example.green.bean.mine.OrderDetailsInfobean;
import com.example.green.config.ApiConfig;
import com.example.green.customs.RoundCornerDialog;
import com.example.green.local_utils.DateUtil;
import com.example.green.local_utils.SPUtils;
import com.example.green.model.MineModel;
import com.example.green.ui.activity.GoodsDetailsActivity;
import com.yiyatech.utils.ext.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class OrderDetailsActivity extends BaseMvpActivity<CommonPresenter, MineModel> implements ICommonView {

    @BindView(R.id.login_back)
    ImageView mLoginBack;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.order_desc)
    TextView mOrderDesc;
    @BindView(R.id.person_name)
    TextView mPersonName;
    @BindView(R.id.tv_site)
    TextView mTvSite;
    @BindView(R.id.person_phone)
    TextView mPersonPhone;
    @BindView(R.id.mTv_storeName)
    TextView mStoreName;
    @BindView(R.id.details_recycler)
    RecyclerView mDetailsRecycler;
    @BindView(R.id.order_sn)
    TextView mOrderSn;
    @BindView(R.id.order_time)
    TextView mOrderTime;
    @BindView(R.id.order_amount)
    TextView mOrderAmount;
    @BindView(R.id.rl_state)
    RelativeLayout mRlState;
    @BindView(R.id.check_logistics)
    TextView mCheck;
    @BindView(R.id.tv_type)
    TextView mTvType;

    private String order_id;
    private String key;
    private static final String TAG = "OrderDetailsActivity";
    private List<OrderDetailsInfobean.ResultBean.GoodsListBean> mGoodsListBeans;
    private OrderDetailsGoodsItemAdapter mOrderDetailsGoodsItemAdapter;
    private String mExpress_code;
    private String mExpress_name;
    private String mShipping_code;
    private int mOrder_state;

    @Override
    protected void initView() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        Intent intent = getIntent();
        order_id = intent.getStringExtra("order_id");
        key = SPUtils.getInstance().getValue(SPUtils.KEY_USER_TOKEN, "");
        mGoodsListBeans = new ArrayList<>();
        mOrderDetailsGoodsItemAdapter = new OrderDetailsGoodsItemAdapter(R.layout.layout_child_order_goods_item, mGoodsListBeans);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mDetailsRecycler.setLayoutManager(layoutManager);
        mDetailsRecycler.setNestedScrollingEnabled(false); //禁止滑动
        mDetailsRecycler.setAdapter(mOrderDetailsGoodsItemAdapter);

        mOrderDetailsGoodsItemAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.item_goods:
                        Intent intent = new Intent(OrderDetailsActivity.this, GoodsDetailsActivity.class);
                        intent.putExtra("goodsId", mGoodsListBeans.get(position).getGoods_id() + "");
                        overridePendingTransition(R.anim.in_from_right,
                                R.anim.out_to_left);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getData(ApiConfig.ORDER_DETAILS, key, order_id);
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
        return R.layout.activity_order_details;
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.ORDER_DETAILS:
                OrderDetailsInfobean orderDetailsInfobean = (OrderDetailsInfobean) t[0];
                if (null != orderDetailsInfobean && orderDetailsInfobean.getCode().equals("200")) {
                    String state_desc = orderDetailsInfobean.getResult().getState_desc(); // 订单状态
                    String store_name = orderDetailsInfobean.getResult().getStore_name(); // 店铺名称
                    List<OrderDetailsInfobean.ResultBean.GoodsListBean> goods_list = orderDetailsInfobean.getResult().getGoods_list();
                    mGoodsListBeans.addAll(goods_list);
                    mOrderDetailsGoodsItemAdapter.notifyDataSetChanged();

                    String order_sn = orderDetailsInfobean.getResult().getOrder_sn(); // 订单编号
                    String order_amount = orderDetailsInfobean.getResult().getOrder_amount(); // 订单总价

                    int add_time = orderDetailsInfobean.getResult().getAdd_time(); // 创建时间
                    String s = String.valueOf(add_time) + "000"; // php 转 android时间戳 后+000
                    String dateToString = DateUtil.getDateToString(Long.valueOf(s), "yyyy-MM-dd HH:mm:ss");

                    // 收货信息
                    OrderDetailsInfobean.ResultBean.ExtendOrderCommonBean.ReciverInfoBean
                            reciver_info = orderDetailsInfobean.getResult().getExtend_order_common().getReciver_info();
                    mOrderDesc.setText(state_desc);
                    mPersonName.setText(orderDetailsInfobean.getResult().getExtend_order_common().getReciver_name());// 收货人
                    mPersonPhone.setText(reciver_info.getMob_phone()); // 收货人手机号
                    mTvSite.setText(reciver_info.getAddress()); // 收货地址
                    mStoreName.setText(store_name);
                    mOrderSn.setText(order_sn);
                    mOrderTime.setText(dateToString);
                    mOrderAmount.setText(order_amount + "元");


                    OrderDetailsInfobean.ResultBean.ExpressInfoBean express_info = orderDetailsInfobean.getResult().getExpress_info();
                    if (null != express_info) {
                        mExpress_code = express_info.getExpress_code(); // 物流信息代码
                        mExpress_name = express_info.getExpress_name(); // 物流公司名称
                        mShipping_code = orderDetailsInfobean.getResult().getShipping_code(); // 物流号
                    }
                    // 10：待付款  20：待发货  30：待收货  40：已完成  0：已取消
                    mOrder_state = orderDetailsInfobean.getResult().getOrder_state();
                    if (mOrder_state == 10) {
                        mCheck.setVisibility(View.GONE);
                        mTvType.setVisibility(View.VISIBLE);
                        mTvType.setText("取消订单");
                    } else if (mOrder_state == 20) {
                        mRlState.setVisibility(View.GONE);
                        mCheck.setVisibility(View.GONE);
                        mTvType.setVisibility(View.GONE);
                    } else if (mOrder_state == 30) {
                        mCheck.setVisibility(View.VISIBLE);
                        mTvType.setVisibility(View.VISIBLE);
                        mCheck.setText("查看物流");
                        mTvType.setText("确认收货");
                    } else if (mOrder_state == 40) {
                        mCheck.setVisibility(View.VISIBLE);
                        mTvType.setVisibility(View.VISIBLE);
                        mCheck.setText("查看物流");
                        mTvType.setText("删除订单");
                    } else if (mOrder_state == 0) {
                        mCheck.setVisibility(View.GONE);
                        mTvType.setVisibility(View.VISIBLE);
                        mTvType.setText("删除订单");
                    }
                }
                break;
            case ApiConfig.CHANGER_ORDER_STATE:
                ChangeOrderStatebean changeOrderStatebean = (ChangeOrderStatebean) t[0];
                if (null != changeOrderStatebean && changeOrderStatebean.getCode().equals("200")) {
                    ToastUtils.show(this, changeOrderStatebean.getMessage());
                    LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(BaseActivity.CHANGE_ORDER_STATE));
                } else {
                    ToastUtils.show(this, changeOrderStatebean.getMessage());
                }
                break;
        }
    }

    @OnClick({R.id.login_back, R.id.check_logistics, R.id.tv_type})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.login_back:
                finish();
                break;
            case R.id.check_logistics:
                Intent intent = new Intent(OrderDetailsActivity.this, LogisticsActivity.class);
                intent.putExtra("express_code", mExpress_code);
                intent.putExtra("express_name", mExpress_name);
                intent.putExtra("shipping_code", mShipping_code);
                intent.putExtra("phone", mPersonPhone.getText().toString().trim());
                startActivity(intent);
                break;
            case R.id.tv_type:
                if (mOrder_state == 10) { // 10：待付款  30：待收货  40：已完成  0：已取消
                    showChangeDialog("确认取消订单", mOrder_state, order_id);
                } else if (mOrder_state == 30) {
                    showChangeDialog("确认收货", mOrder_state, order_id);
                } else if (mOrder_state == 40) {
                    showChangeDialog("确认删除订单", mOrder_state, order_id);
                } else if (mOrder_state == 0) {
                    showChangeDialog("确认删除订单", mOrder_state, order_id);
                }
                break;
        }
    }

    private void showChangeDialog(String msg, final int order_state, final String order_id) {
        View view = View.inflate(this, R.layout.dialog_withdraw_hint, null);
        final RoundCornerDialog roundCornerDialog = new RoundCornerDialog(this, 0, 0, view, R.style.RoundCornerDialog);
        roundCornerDialog.show();
        roundCornerDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        roundCornerDialog.setOnKeyListener(keylistener);//设置点击返回键Dialog不消失

        TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
        TextView tv_logout_cancel = (TextView) view.findViewById(R.id.tv_logout_cancel);
        TextView tv_logout_confirm = (TextView) view.findViewById(R.id.tv_logout_confirm);
        tv_message.setText(msg);
        // 确认
        tv_logout_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                if (order_state == 10) {
                    mPresenter.getData(ApiConfig.CHANGER_ORDER_STATE, key, "order_cancel", order_id);
                } else if (order_state == 30) {
                    mPresenter.getData(ApiConfig.CHANGER_ORDER_STATE, key, "order_receive", order_id);
                } else if (order_state == 40 || order_state == 0) {
                    mPresenter.getData(ApiConfig.CHANGER_ORDER_STATE, key, "order_delete", order_id);
                }
                roundCornerDialog.dismiss();
            }
        });
        // 取消
        tv_logout_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roundCornerDialog.dismiss();
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
}
