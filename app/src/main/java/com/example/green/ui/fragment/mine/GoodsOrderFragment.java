package com.example.green.ui.fragment.mine;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.green.R;
import com.example.green.adapter.mine.order.GoodsOrderItemAdapter;
import com.example.green.base.BaseFragment;
import com.example.green.base.BaseMvpFragment;
import com.example.green.base.CommonPresenter;
import com.example.green.base.ICommonView;
import com.example.green.bean.MyOrderbean;
import com.example.green.bean.mine.ChangeOrderStatebean;
import com.example.green.bean.pay.AcquireOrderInfobean;
import com.example.green.config.ApiConfig;
import com.example.green.config.LoadConfig;
import com.example.green.customs.RoundCornerDialog;
import com.example.green.local_utils.SPUtils;
import com.example.green.model.MineModel;
import com.example.green.ui.activity.PayModeActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyatech.utils.ext.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class GoodsOrderFragment extends BaseMvpFragment<CommonPresenter, MineModel> implements ICommonView {


    @BindView(R.id.order_recyclerview)
    RecyclerView mOrderRecyclerview;
    @BindView(R.id.tv_occupied)
    TextView mTvOccupied;
    @BindView(R.id.SmartRefresh)
    SmartRefreshLayout mSmartRefreshLayout;
    private String status_type;
    private int page;
    private int pageSize = 5;
    private static final String TAG = "GoodsOrderFragment";
    private List<MyOrderbean.ResultBean.OrderGroupListBean> mGroupListBeans;
    private GoodsOrderItemAdapter mGoodsOrderAdapter;
    private MyOrderbean mMyOrderbean;
    private String mToken;


    public static GoodsOrderFragment newInstance(String status_type) {
        GoodsOrderFragment myFragment = new GoodsOrderFragment();
        Bundle bundle = new Bundle();
        /*
        state_new:待付款 ，
        state_pay：待发货 ，
        state_send：待收货 ，
        state_notakes：待自提 ，
        state_noeval：带评价 ，
        state_success：已完成 ，
        state_cancel：已经取消的订单 ，
        为空  表示全部订单
        */
        bundle.putString("state_type", status_type); // 订单状态
        myFragment.setArguments(bundle);
        return myFragment;
    }

    @Override
    protected CommonPresenter initPresenter() {
        return new CommonPresenter();
    }

    @Override
    protected MineModel initModel() {
        return new MineModel();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_goods_order;
    }

    @Override
    protected void initView() {
        mToken = SPUtils.getInstance().getValue(SPUtils.KEY_USER_TOKEN, "");
        Bundle bundle = getArguments();
        if (null != bundle) {
            status_type = bundle.getString("state_type");
        }
        mGroupListBeans = new ArrayList<>();
        mGoodsOrderAdapter = new GoodsOrderItemAdapter(R.layout.layout_goodsorder, mGroupListBeans);
        mOrderRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mOrderRecyclerview.setAdapter(mGoodsOrderAdapter);

        mGoodsOrderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_type:
                        String pay_sn = mGroupListBeans.get(position).getPay_sn();
                        mPresenter.getData(ApiConfig.SHOPPING_THIRD, mToken, pay_sn);
                        break;
                }
            }
        });
        // 回调中处理 子订单（修改状态）
        mGoodsOrderAdapter.setChangeState(new GoodsOrderItemAdapter.changeState() {
            @Override
            public void click(int order_id, int order_state) {
                if (order_state == 10) {
                    showChangeDialog("确认取消订单", order_state, order_id);
                } else if (order_state == 30) {
                    showChangeDialog("确认收货", order_state, order_id);
                } else if (order_state == 40) {
                    showChangeDialog("确认删除订单", order_state, order_id);
                } else if (order_state == 0) {
                    showChangeDialog("确认删除订单", order_state, order_id);
                }
            }
        });

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                mPresenter.getData(ApiConfig.GOODSORDER, mToken, status_type, page, pageSize, LoadConfig.REFRESH);
            }
        });
        mSmartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (mMyOrderbean.isHasmore() == true) {
                    mPresenter.getData(ApiConfig.GOODSORDER, mToken, status_type, ++page, pageSize, LoadConfig.LOADMORE);
                } else {
                    mSmartRefreshLayout.finishLoadmore();
                }
            }
        });
    }

    private void showChangeDialog(String msg, final int order_state, final int order_id) {
        View view = View.inflate(getContext(), R.layout.dialog_withdraw_hint, null);
        final RoundCornerDialog roundCornerDialog = new RoundCornerDialog(getContext(), 0, 0, view, R.style.RoundCornerDialog);
        roundCornerDialog.show();
        roundCornerDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        roundCornerDialog.setOnKeyListener(keylistener);//设置点击返回键Dialog不消失

        TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
        TextView tv_logout_cancel = (TextView) view.findViewById(R.id.tv_logout_cancel);
        TextView tv_logout_confirm = (TextView) view.findViewById(R.id.tv_logout_confirm);
        tv_message.setText(msg);
        // 确定
        tv_logout_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                if (order_state == 10) {
                    mPresenter.getData(ApiConfig.CHANGER_ORDER_STATE, mToken, "order_cancel", order_id + "");
                } else if (order_state == 30) {
                    mPresenter.getData(ApiConfig.CHANGER_ORDER_STATE, mToken, "order_receive", order_id + "");
                } else if (order_state == 40 || order_state == 0) {
                    mPresenter.getData(ApiConfig.CHANGER_ORDER_STATE, mToken, "order_delete", order_id + "");
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

    @Override
    protected void initData() {
        page = 1;
        mPresenter.getData(ApiConfig.GOODSORDER, mToken, status_type, page, pageSize, LoadConfig.NORMAL);
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError: " + e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.GOODSORDER:
                mMyOrderbean = (MyOrderbean) t[0];
                if (null != mMyOrderbean && mMyOrderbean.getCode().equals("200")) {
                    List<MyOrderbean.ResultBean.OrderGroupListBean> order_group_list = mMyOrderbean.getResult().getOrder_group_list();

                    if (order_group_list.size() > 0) {
                        mTvOccupied.setVisibility(View.GONE);
                        mOrderRecyclerview.setVisibility(View.VISIBLE);
                    } else {
                        mTvOccupied.setVisibility(View.VISIBLE);
                        mOrderRecyclerview.setVisibility(View.GONE);
                    }

                    // 加载数据
                    int loadMode = (int) t[1];
                    if (loadMode == LoadConfig.NORMAL) {
                        mGroupListBeans.addAll(order_group_list);
                    } else if (loadMode == LoadConfig.REFRESH) {
                        mGroupListBeans.clear();
                        mGroupListBeans.addAll(order_group_list);
                        mSmartRefreshLayout.finishRefresh();
                    } else if (loadMode == LoadConfig.LOADMORE) {
                        mGroupListBeans.addAll(order_group_list);
                        mSmartRefreshLayout.finishLoadmore();
                    }
                    mGoodsOrderAdapter.notifyDataSetChanged();
                }
                break;
            case ApiConfig.CHANGER_ORDER_STATE:
                ChangeOrderStatebean changeOrderStatebean = (ChangeOrderStatebean) t[0];
                if (null != changeOrderStatebean && changeOrderStatebean.getCode().equals("200")) {
                    ToastUtils.show(getContext(), changeOrderStatebean.getMessage());
                    LocalBroadcastManager.getInstance(getContext()).sendBroadcast(new Intent(BaseFragment.CHANGE_ORDER_STATE));

                    // 刷新列表
                    mPresenter.getData(ApiConfig.GOODSORDER, mToken, status_type, page, pageSize, LoadConfig.REFRESH);
                }
                break;
            case ApiConfig.SHOPPING_THIRD:
                AcquireOrderInfobean acquireOrderInfobean = (AcquireOrderInfobean) t[0];
                if (null != acquireOrderInfobean && acquireOrderInfobean.getCode().equals("200")) {
                    AcquireOrderInfobean.ResultBean.PayInfoBean pay_info = acquireOrderInfobean.getResult().getPay_info();
                    String member_available_pd = pay_info.getMember_available_pd(); // 用户可用储值卡余额
                    String pay_sn = pay_info.getPay_sn(); // 支付单号
                    String pay_amount = pay_info.getPay_amount(); // 订单价格

                    Intent intent = new Intent(getContext(), PayModeActivity.class);
                    intent.putExtra("pay_sn", pay_sn);
                    intent.putExtra("pay_amount", pay_amount);
                    intent.putExtra("member_available_pd", member_available_pd);
                    startActivity(intent);
                } else {
                    ToastUtils.show(getContext(), acquireOrderInfobean.getMessage());
                }
                break;
        }
    }

    @Override
    protected void receiverBroadCast(Intent intent) {
        super.receiverBroadCast(intent);

        switch (intent.getAction()) {
            case PAY_SUCCESS:// 订单支付成功
            case CHANGE_ORDER_STATE:// 修改订单状态

                mPresenter.getData(ApiConfig.GOODSORDER, mToken, status_type, page, pageSize, LoadConfig.REFRESH);
                break;
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
