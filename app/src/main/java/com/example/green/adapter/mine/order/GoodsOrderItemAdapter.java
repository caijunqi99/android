package com.example.green.adapter.mine.order;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.green.R;
import com.example.green.bean.MyOrderbean;
import com.example.green.ui.activity.mine.OrderDetailsActivity;

import java.util.List;

/**
 * @ProjectName: Green
 * @Package: com.example.green.adapter.shoptrolley
 * @ClassName: GoodsChildOrderAdapter
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: 所有订单列表
 * @CreateDate: 2020/1/8 11:06
 */
public class GoodsOrderItemAdapter extends BaseQuickAdapter<MyOrderbean.ResultBean.OrderGroupListBean, BaseViewHolder> {
    private static final String TAG = "GoodsOrderItemAdapter";

    public GoodsOrderItemAdapter(int layoutResId, @Nullable List<MyOrderbean.ResultBean.OrderGroupListBean> data) {
        super(layoutResId, data);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(final BaseViewHolder helper, MyOrderbean.ResultBean.OrderGroupListBean item) {
        MyOrderbean.ResultBean.OrderGroupListBean groupListBean = item;

        if (null != groupListBean) {
            TextView type = helper.getView(R.id.tv_type);
            TextView orderInfo = helper.getView(R.id.order_info);
            TextView state = helper.getView(R.id.order_state);
            final int order_state = groupListBean.getOrder_state();

            String state_desc = groupListBean.getState_desc();
            state.setText(state_desc);
            orderInfo.setText("订单总价:" + groupListBean.getOrder_amount() + "元");
            if (order_state == 10) {
                type.setVisibility(View.VISIBLE);
                type.setText("去付款");
            } else if (order_state == 20) {
                type.setVisibility(View.GONE);
            } else if (order_state == 30) {
                type.setVisibility(View.GONE);
            } else if (order_state == 40) {
                type.setVisibility(View.GONE);
            } else if (order_state == 0) {
                type.setVisibility(View.GONE);
            }

            final List<MyOrderbean.ResultBean.OrderGroupListBean.OrderListBean> order_list = groupListBean.getOrder_list();

            RecyclerView child_order = helper.getView(R.id.child_goods_recyclerview);
            ChildOrderItemAdapter goodsChildOrderAdapter = new ChildOrderItemAdapter(R.layout.layout_child_order, groupListBean.getOrder_list());
            child_order.setLayoutManager(new LinearLayoutManager(mContext));
            child_order.setNestedScrollingEnabled(false); //禁止滑动
            child_order.setAdapter(goodsChildOrderAdapter);
            goodsChildOrderAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    switch (view.getId()) {
                        case R.id.item_goods:
                            Intent intent_details = new Intent(mContext, OrderDetailsActivity.class);
                            intent_details.putExtra("order_id", order_list.get(position).getOrder_id() + "");
                            mContext.startActivity(intent_details);
                            break;
                        case R.id.state_change:
                            if (null != mChangeState) {
                                mChangeState.click(order_list.get(position).getOrder_id(), order_list.get(position).getOrder_state());
                            }
                            break;
                    }
                }
            });
            helper.addOnClickListener(R.id.tv_type);
        }
    }

    private changeState mChangeState;

    public void setChangeState(changeState pChangeState) {
        mChangeState = pChangeState;
    }

    public interface changeState {
        void click(int order_id, int order_state);
    }
}
